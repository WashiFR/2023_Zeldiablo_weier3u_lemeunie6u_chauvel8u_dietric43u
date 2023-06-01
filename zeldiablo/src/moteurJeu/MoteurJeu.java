package moteurJeu;

//https://github.com/zarandok/megabounce/blob/master/MainCanvas.java

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.MalformedURLException;

// copied from: https://gist.github.com/james-d/8327842
// and modified to use canvas drawing instead of shapes

public class MoteurJeu extends Application {

    /**
     * gestion du temps : nombre de frame par secondes et temps par iteration
     */
    private static double FPS = 100;
    private static double dureeFPS = 1000 / (FPS + 1);

    /**
     * taille par defaut
     */
    private static double WIDTH = 800;
    private static double HEIGHT = 600;

    /**
     * statistiques sur les frames
     */
    private final FrameStats frameStats = new FrameStats();

    /**
     * jeu en Cours et renderer du jeu
     */
    private static Jeu jeu = null;
    private static DessinJeu dessin = null;

    /**
     * touches appuyee entre deux frame
     */
    Clavier controle = new Clavier();

    /**
     * lancement d'un jeu
     *
     * @param jeu    jeu a lancer
     * @param dessin dessin du jeu
     */
    public static void launch(Jeu jeu, DessinJeu dessin) {
        // le jeu en cours et son afficheur
        MoteurJeu.jeu = jeu;
        MoteurJeu.dessin = dessin;

        // si le jeu existe, on lance le moteur de jeu
        if (jeu != null)
            launch();
    }

    /**
     * frame par secondes
     *
     * @param FPSSouhaitees nombre de frames par secondes souhaitees
     */
    public static void setFPS(int FPSSouhaitees) {
        FPS = FPSSouhaitees;
        dureeFPS = 1000 / (FPS + 1);
    }

    public static void setTaille(double width, double height) {
        WIDTH = width;
        HEIGHT = height;
    }


    //#################################
    // SURCHARGE Application
    //#################################

    @Override
    /**
     * creation de l'application avec juste un canvas et des statistiques
     */
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        root.backgroundProperty().setValue(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(Color.CORNFLOWERBLUE, null, null)));
        Button jouer = new Button("Jouer");
        Button quitter = new Button("Quitter");
        //jouer.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(Color.DARKSEAGREEN, null, null)));
        //quitter.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(Color.DARKSEAGREEN, null, null)));
        ImageView image = new ImageView(new Image("file:./src/gameLaby/laby/img/r.png"));
        ImageView porte = new ImageView(new Image("file:./src/gameLaby/laby/img/porte.png"));
        porte.setX(180);
        porte.setFitHeight(600);
        porte.setFitWidth(400);
        jouer.setLayoutX(200);
        jouer.setLayoutY(500);

        quitter.setLayoutX(450);
        quitter.setLayoutY(500);
        jouer.setFont(javafx.scene.text.Font.font(30));
        quitter.setFont(javafx.scene.text.Font.font(30));

        jouer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                // initialisation du canvas de dessin et du container
                final Canvas canvas = new Canvas();
                final Pane canvasContainer = new Pane(canvas);
                canvas.widthProperty().bind(canvasContainer.widthProperty());
                canvas.heightProperty().bind(canvasContainer.heightProperty());

                // affichage des stats
                final Label stats = new Label();
                stats.textProperty().bind(frameStats.textProperty());

                // ajout des statistiques en bas de la fenetre
                final BorderPane root = new BorderPane();
                root.setCenter(canvasContainer);
                root.setBottom(stats);

                // creation de la scene
                final Scene scene = new Scene(root, WIDTH, HEIGHT);
                primaryStage.setScene(scene);
                primaryStage.show();


                // listener clavier
                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        controle.appuyerTouche(event);
                    }
                });

                scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        controle.relacherTouche(event);
                    }
                });


                // creation du listener souris
                canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                if (event.getClickCount() == 2) {
                                    jeu.init();
                                }
                            }
                        });

                // lance la boucle de jeu
                startAnimation(canvas);
            }});


        quitter.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.close();
            }
        });



        root.getChildren().addAll(image,porte, jouer, quitter);


        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    /**
     * gestion de l'animation (boucle de jeu)
     *
     * @param canvas le canvas sur lequel on est synchronise
     */
    private void startAnimation(final Canvas canvas) {
        // stocke la derniere mise e jour
        final LongProperty lastUpdateTime = new SimpleLongProperty(0);

        // timer pour boucle de jeu
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {

                // si jamais passe dans la boucle, initialise le temps
                if (lastUpdateTime.get() == 0) {
                    lastUpdateTime.set(timestamp);
                }

                // mesure le temps ecoule depuis la derniere mise a jour
                long elapsedTime = timestamp - lastUpdateTime.get();
                double dureeEnMilliSecondes = elapsedTime / 1_000_000.0;


                // si le temps ecoule depasse le necessaire pour FPS souhaite
                if (dureeEnMilliSecondes > dureeFPS) {
                    // met a jour le jeu en passant les touches appuyees
                    jeu.update(dureeEnMilliSecondes / 1_000., controle);

                    // dessine le jeu
                    try {
                        dessin.dessinerJeu(jeu, canvas);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }

                    // ajoute la duree dans les statistiques
                    frameStats.addFrame(elapsedTime);

                    // met a jour la date de derniere mise a jour
                    lastUpdateTime.set(timestamp);
                }

            }
        };

        // lance l'animation
        timer.start();
    }
}