import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static javafx.scene.control.Alert.AlertType.*;


public class Main extends Application {

    static MediaRental mediaRental = new MediaRental();

    @Override
    public void start(Stage stage) {
        
//Author: Omar Qalalweh

        loadData();// calling method to load and retrieve data from the files .

        //For getting the User Screen size
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        //borderpane layout and adding it to the scene for every page
        BorderPane mainPagePane = new BorderPane();
        Scene mainPage = new Scene(mainPagePane,screenSize.getWidth(), screenSize.getHeight());  //creating scene with same screen size for every page

        //-----------------------------------main page menu pages ----------------------------------
        BorderPane cusPane = new BorderPane();
        Scene customerPage = new Scene(cusPane,screenSize.getWidth(), screenSize.getHeight());

        BorderPane mediaPane = new BorderPane();
        Scene mediaPage = new Scene(mediaPane,screenSize.getWidth(), screenSize.getHeight());

        BorderPane rentPane = new BorderPane();
        Scene rentPage = new Scene(rentPane,screenSize.getWidth(), screenSize.getHeight());

         // ----------------------------------- cus menu pages ------------------------------------
        BorderPane addCusPane = new BorderPane();
        Scene addCusPage = new Scene(addCusPane,screenSize.getWidth(),screenSize.getHeight());

        BorderPane delCusPane = new BorderPane();
        Scene delCusPage = new Scene(delCusPane,screenSize.getWidth(),screenSize.getHeight());

        BorderPane updateCusPane = new BorderPane();
        Scene updateCusPage = new Scene(updateCusPane,screenSize.getWidth(),screenSize.getHeight());

        BorderPane searchCusPane = new BorderPane();
        Scene searchCusPage = new Scene(searchCusPane,screenSize.getWidth(),screenSize.getHeight());



        // ------------------------------------ Media menu page ------------------------------------
        BorderPane addMediaPane = new BorderPane();
        Scene addMediaPage = new Scene(addMediaPane,screenSize.getWidth(),screenSize.getHeight());

        BorderPane delMediaPane = new BorderPane();
        Scene delMediaPage = new Scene(delMediaPane,screenSize.getWidth(),screenSize.getHeight());

        BorderPane updateMediaPane = new BorderPane();
        Scene updateMediaPage = new Scene(updateMediaPane,screenSize.getWidth(),screenSize.getHeight());

        BorderPane searchMediaPane = new BorderPane();
        Scene searchMediaPage = new Scene(searchMediaPane,screenSize.getWidth(),screenSize.getHeight());

        BorderPane printMediaPane = new BorderPane();
        Scene printMediaPage = new Scene(printMediaPane,screenSize.getWidth(),screenSize.getHeight());


        // ------------------------------------ Rent menu page ------------------------------------

        BorderPane rentToCusPane = new BorderPane();
        Scene rentToCusPage = new Scene(rentToCusPane,screenSize.getWidth(),screenSize.getHeight());

        BorderPane printRequestedPane = new BorderPane();
        Scene printRequestedPage = new Scene(printRequestedPane,screenSize.getWidth(),screenSize.getHeight());

        BorderPane printRentedPane = new BorderPane();
        Scene printRentedPage = new Scene(printRentedPane,screenSize.getWidth(),screenSize.getHeight());

        BorderPane returnRentedPane = new BorderPane();
        Scene returnRentedPage = new Scene(returnRentedPane,screenSize.getWidth(),screenSize.getHeight());

        BorderPane setLimitPane = new BorderPane();
        Scene setLimitPage = new Scene(setLimitPane,screenSize.getWidth(),screenSize.getHeight());


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Homepage ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        //borderpane layout and adding it to the scene
        mainPagePane.setPadding(new Insets(25,40,25,40));
        mainPage.getStylesheets().add("mainPageStyle.css");

        //Name logo for the main page
        Image nameLogo  = new Image("namelogo.png");
        ImageView nameLogoView = new ImageView(nameLogo);
        nameLogoView.setFitHeight(80);
        nameLogoView.setFitWidth(700);
        mainPagePane.setTop(nameLogoView);
        BorderPane.setAlignment(nameLogoView, Pos.CENTER);

        //Buttons in the homepage
        Button customerB = new Button("Customers");
        Button mediaB = new Button("Media");
        Button rentB = new Button("Rent");

        //adding icons to the buttons
        Image cusIcon = new Image("cusIcon.png");
        ImageView cusIconView = new ImageView(cusIcon);
        cusIconView.setFitHeight(35);
        cusIconView.setFitWidth(35);
        customerB.setGraphic(cusIconView);
        customerB.setGraphicTextGap(15);

        Image mediaIcon = new Image("music.png");
        ImageView mediaIconView = new ImageView(mediaIcon);
        mediaIconView.setFitWidth(35);
        mediaIconView.setFitHeight(35);
        mediaB.setGraphic(mediaIconView);
        mediaB.setGraphicTextGap(15);

        Image rentIcon = new Image("rent.png");
        ImageView rentIconView = new ImageView(rentIcon);
        rentIconView.setFitWidth(35);
        rentIconView.setFitHeight(35);
        rentB.setGraphic(rentIconView);
        rentB.setGraphicTextGap(15);

        customerB.setOnAction(e -> stage.setScene(customerPage));  //actions for the buttons
        mediaB.setOnAction(e -> stage.setScene(mediaPage));
        rentB.setOnAction(e -> stage.setScene(rentPage));

        // adding button to a Vbox
        VBox vBox = new VBox();
        vBox.setSpacing(50);
        vBox.setPadding(new Insets(50,50,50,50));
        vBox.getChildren().addAll(customerB,mediaB,rentB);

        //HBox for element in the center of borderpane
        HBox hBox = new HBox();
        mainPagePane.setCenter(hBox);
        hBox.setSpacing(100);
        hBox.setPadding(new Insets(80,50,50,50));
        BorderPane.setAlignment(hBox,Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);

        //adding the vbox of button to the HBox
        hBox.getChildren().add(vBox);

        // ADDING COMPANY LOGO TO THE HOME PAGE
        Image comLogo = new Image("comlogo.png");
        ImageView comLogoView = new ImageView(comLogo);
        comLogoView.setFitWidth(500);
        comLogoView.setFitHeight(350);
        hBox.getChildren().add(comLogoView);

        //exit button
        Button exitB = new Button("EXIT");
        mainPagePane.setBottom(exitB);
        BorderPane.setAlignment(exitB,Pos.CENTER);

        exitB.setOnAction(e -> {    //setting action for the button (lambda expression)
            stage.hide();
            System.exit(0);
        });
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Customer Pages ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        cusPane.setPadding(new Insets(50,50,50,50));
        customerPage.getStylesheets().add("mainPageStyle.css");

        //buttons in customer page
        Button cusB1 = new Button("Add New Customer");
        Button cusB2 = new Button("Delete Customer");
        Button cusB3 = new Button("Update Information about Customer");
        Button cusB4 = new Button("Search a Customer by ID");

        VBox cusMenu = new VBox();
        cusMenu.setSpacing(25);
        cusMenu.setPadding(new Insets(30,30,30,30));
        cusMenu.getChildren().addAll(cusB1,cusB2,cusB3,cusB4);
        cusMenu.setAlignment(Pos.CENTER);
        cusPane.setCenter(cusMenu);
        BorderPane.setAlignment(cusMenu,Pos.CENTER);


        //BACK buttons
        Button backBt1 = new Button("Back");

        Image backImage1 = new Image("back.png");
        ImageView backImageView1 = new ImageView(backImage1);
        backImageView1.setFitWidth(30);
        backImageView1.setFitHeight(30);

        backBt1.setGraphic(backImageView1);
        backBt1.setGraphicTextGap(15);

        cusPane.setBottom(backBt1);
        BorderPane.setAlignment(backBt1,Pos.CENTER);

        backBt1.setOnAction(e -> stage.setScene(mainPage) );  //Actions for the  buttons
        cusB1.setOnAction(e-> stage.setScene(addCusPage) );
        cusB2.setOnAction(e-> stage.setScene(delCusPage) );
        cusB3.setOnAction(e-> stage.setScene(updateCusPage));
        cusB4.setOnAction(e->stage.setScene(searchCusPage));


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Add customer Page ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        addCusPane.setPadding(new Insets(50,50,50,50));
        addCusPage.getStylesheets().add("mainPageStyle.css");


        //page labels and textField and toggle group
        Label cusIDLabel_addPage =new Label("Customer ID");
        cusIDLabel_addPage.setAlignment(Pos.CENTER);
        TextField cusIdIn_addPage = new TextField();
        cusIdIn_addPage.setPromptText("Enter Customer ID");
        cusIdIn_addPage.setAlignment(Pos.CENTER);


        Label cusNameLabel_addPage =new Label("Customer Name");
        cusNameLabel_addPage.setAlignment(Pos.CENTER);
        TextField cusNameIn_addPage = new TextField();
        cusNameIn_addPage.setPromptText(" Customer Name");
        cusNameIn_addPage.setAlignment(Pos.CENTER);
        cusNameIn_addPage.setDisable(true);

        Label cusAddressLabel_addPage =new Label(" Address");
        cusAddressLabel_addPage.setAlignment(Pos.CENTER);

        TextField cusAddressIn_addPage = new TextField();
        cusAddressIn_addPage.setPromptText("Enter Customer Address");
        cusAddressIn_addPage.setAlignment(Pos.CENTER);
        cusAddressIn_addPage.setDisable(true);

        Label cusMobileLabel_addPage =new Label(" Mobile Number");
        cusMobileLabel_addPage.setAlignment(Pos.CENTER);

        TextField cusMobileIn_addPage = new TextField();
        cusMobileIn_addPage.setPromptText("Enter Customer Mobile Number");
        cusMobileIn_addPage.setAlignment(Pos.CENTER);
        cusMobileIn_addPage.setDisable(true);

        //label for the plan
        Label planSettingLabel = new Label("Plan : ");
        planSettingLabel.setAlignment(Pos.CENTER);
        //toggle group for the plan (2 radio buttons)

        ToggleGroup planSettings = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Limited");
        rb1.setToggleGroup(planSettings);
        rb1.setDisable(true);

        RadioButton rb2 = new RadioButton("Unlimited");
        rb2.setToggleGroup(planSettings);
        rb2.setDisable(true);

        HBox rBContainer = new HBox();
        rBContainer.setAlignment(Pos.CENTER);
        rBContainer.setSpacing(30);
        rBContainer.getChildren().addAll(rb1,rb2);


        //grid pane for the textField and labels
        GridPane add_cus_page_gp = new GridPane();

        add_cus_page_gp.add(cusIDLabel_addPage,0,0);
        add_cus_page_gp.add(cusIdIn_addPage,1,0);

        add_cus_page_gp.add(cusNameLabel_addPage,0,1);
        add_cus_page_gp.add(cusNameIn_addPage,1,1);

        add_cus_page_gp.add(cusAddressLabel_addPage,0,2);
        add_cus_page_gp.add(cusAddressIn_addPage,1,2);

        add_cus_page_gp.add(cusMobileLabel_addPage,0,3);
        add_cus_page_gp.add(cusMobileIn_addPage,1,3);

        add_cus_page_gp.add(planSettingLabel,0,4);
        add_cus_page_gp.add(rBContainer,1,4);

        addCusPane.setCenter(add_cus_page_gp);

        add_cus_page_gp.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(add_cus_page_gp,Pos.CENTER);
        add_cus_page_gp.setVgap(15);
        add_cus_page_gp.setHgap(50);

        //for enabling textField when the previous text is written correctly

        cusIdIn_addPage.setOnKeyReleased(e-> cusNameIn_addPage.setDisable(cusIdIn_addPage.getText().trim().isEmpty() || !cusIdIn_addPage.getText().trim().matches("[0-9]+")));

        cusNameIn_addPage.setOnKeyReleased(e-> cusAddressIn_addPage.setDisable(cusNameIn_addPage.getText().trim().isEmpty() || cusNameIn_addPage.getText().matches(".*\\d.*")));

        cusAddressIn_addPage.setOnKeyReleased(e->cusMobileIn_addPage.setDisable(cusAddressIn_addPage.getText().trim().isEmpty()) );

        cusMobileIn_addPage.setOnKeyReleased(e-> {
            if (!cusMobileIn_addPage.getText().trim().isEmpty() && cusMobileIn_addPage.getText().trim().matches("[0-9]+")){
                rb1.setDisable(false);
                rb2.setDisable(false);
            }else {
                rb1.setDisable(true);
                rb2.setDisable(true);
            }
        }  );

        //add and Back buttons
        Button backBt_addCusPage = new Button("Back");

        Image backImage_addCusPage = new Image("back.png");
        ImageView backImageView_addCusPage = new ImageView(backImage_addCusPage);
        backImageView_addCusPage.setFitWidth(30);
        backImageView_addCusPage.setFitHeight(30);

        backBt_addCusPage.setGraphic(backImageView_addCusPage);
        backBt_addCusPage.setGraphicTextGap(15);

        Button addBT_addCusPage = new Button("Add");
        Image addImage_addCusPage = new Image("plus.png");
        ImageView addImageView_addCusPage = new ImageView(addImage_addCusPage);
        addBT_addCusPage.getStylesheets().add("addButtonStyle.css");
        addImageView_addCusPage.setFitWidth(30);
        addImageView_addCusPage.setFitHeight(30);

        addBT_addCusPage.setGraphic(addImageView_addCusPage);
        addBT_addCusPage.setGraphicTextGap(15);

        HBox backAddContainer_addCusPage = new HBox();
        backAddContainer_addCusPage.setAlignment(Pos.CENTER);
        backAddContainer_addCusPage.setSpacing(100);
        backAddContainer_addCusPage.getChildren().addAll(backBt_addCusPage,addBT_addCusPage);

        addCusPane.setBottom(backAddContainer_addCusPage);
        BorderPane.setAlignment(backAddContainer_addCusPage,Pos.CENTER);

        //buttons action
        backBt_addCusPage.setOnAction(e-> {
            stage.setScene(customerPage);

            rb1.setSelected(false);      //reset the text field and buttons
            rb2.setSelected(false);
            cusIdIn_addPage.clear();
            cusNameIn_addPage.clear();
            cusAddressIn_addPage.clear();
            cusMobileIn_addPage.clear();
            rb1.setDisable(true);
            rb2.setDisable(true);
            cusNameIn_addPage.setDisable(true);
            cusAddressIn_addPage.setDisable(true);
            cusMobileIn_addPage.setDisable(true);

        });

        addBT_addCusPage.setOnAction(e->{
            if(cusIdIn_addPage.getText().trim().matches("[0-9]+") && !cusNameIn_addPage.getText().matches(".*\\d.*") && cusMobileIn_addPage.getText().trim().matches("[0-9]+") ){
                String id = cusIdIn_addPage.getText();
                String name = cusNameIn_addPage.getText();
                String address = cusAddressIn_addPage.getText();
                String mobileNum = cusMobileIn_addPage.getText();

                String plan;
                if (rb1.isSelected()){
                    plan = "limited";
                }else {
                    plan = "unlimited";
                }

                try{
                    mediaRental.addCustomer(id,name,mobileNum,address,plan);
                    showAlert("Success","The Customer added Successfully",0);
                    updateAndSaveCustomer();

                }catch (Exception exception){
                    showAlert("Error",exception.getMessage(),1);
                }
            }else {
                showAlert("Error","Please enter correct data ",1);
            }

        });

        cusIdIn_addPage.setOnKeyTyped(event->{      //clear text field when the id changed after getting the result
            cusNameIn_addPage.clear();
            cusNameIn_addPage.setDisable(true);
            cusAddressIn_addPage.clear();
            cusAddressIn_addPage.setDisable(true);
            cusMobileIn_addPage.clear();
            cusMobileIn_addPage.setDisable(true);
            rb1.setSelected(false);
            rb1.setDisable(true);
            rb2.setSelected(false);
            rb2.setDisable(true);
        });
        cusNameIn_addPage.setOnKeyTyped(event -> {

            if(!(cusIdIn_addPage.getText().trim().matches("[0-9]+") && !cusNameIn_addPage.getText().matches(".*\\d.*"))) {
                cusAddressIn_addPage.clear();
                cusAddressIn_addPage.setDisable(true);
                cusMobileIn_addPage.clear();
                cusMobileIn_addPage.setDisable(true);
                rb1.setSelected(false);
                rb1.setDisable(true);
                rb2.setSelected(false);
                rb2.setDisable(true);
            }
        });


        cusMobileIn_addPage.setOnKeyTyped(event -> {

            if(!(cusIdIn_addPage.getText().trim().matches("[0-9]+") && !cusNameIn_addPage.getText().matches(".*\\d.*")&& cusMobileIn_addPage.getText().trim().matches("[0-9]+"))) {
                rb1.setSelected(false);
                rb1.setDisable(true);
                rb2.setSelected(false);
                rb2.setDisable(true);
            }
        });  //done


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ del customer page ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        delCusPane.setPadding(new Insets(50,50,50,50));
        delCusPage.getStylesheets().add("mainPageStyle.css");

        //page labels and textField and toggle group
        Label cusIDLabel_delPage =new Label("Customer ID");
        cusIDLabel_delPage.setAlignment(Pos.CENTER);
        TextField cusIdIn_delPage = new TextField();
        cusIdIn_delPage.setPromptText("Enter Customer ID");
        cusIdIn_delPage.setAlignment(Pos.CENTER);


        Label cusNameLabel_delPage =new Label("Customer Name");
        cusNameLabel_delPage.setAlignment(Pos.CENTER);
        TextField cusNameIn_delPage = new TextField();
        cusNameIn_delPage.setAlignment(Pos.CENTER);
        cusNameIn_delPage.setEditable(false);

        Label cusAddressLabel_delPage =new Label("Customer Address");
        cusAddressLabel_delPage.setAlignment(Pos.CENTER);

        TextField cusAddressIn_delPage = new TextField();
        cusAddressIn_delPage.setAlignment(Pos.CENTER);
        cusAddressIn_delPage.setEditable(false);

        Label cusMobileLabel_delPage =new Label("Customer Mobile");
        cusMobileLabel_delPage.setAlignment(Pos.CENTER);

        TextField cusMobileIn_delPage = new TextField();
        cusMobileIn_delPage.setAlignment(Pos.CENTER);
        cusMobileIn_delPage.setEditable(false);

        Label cusPlanLabel_delPage =new Label("Customer Plan");
        cusPlanLabel_delPage.setAlignment(Pos.CENTER);

        TextField cusPlanIn_delPage = new TextField();
        cusPlanIn_delPage.setAlignment(Pos.CENTER);
        cusPlanIn_delPage.setEditable(false);


        GridPane del_cus_page_gp = new GridPane();

        del_cus_page_gp.add(cusIDLabel_delPage,0,0);
        del_cus_page_gp.add(cusIdIn_delPage,1,0);

        del_cus_page_gp.add(cusNameLabel_delPage,0,1);
        del_cus_page_gp.add(cusNameIn_delPage,1,1);

        del_cus_page_gp.add(cusAddressLabel_delPage,0,2);
        del_cus_page_gp.add(cusAddressIn_delPage,1,2);

        del_cus_page_gp.add(cusMobileLabel_delPage,0,3);
        del_cus_page_gp.add(cusMobileIn_delPage,1,3);

        del_cus_page_gp.add(cusPlanLabel_delPage,0,4);
        del_cus_page_gp.add(cusPlanIn_delPage,1,4);

        delCusPane.setCenter(del_cus_page_gp);

        del_cus_page_gp.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(del_cus_page_gp,Pos.CENTER);
        del_cus_page_gp.setVgap(15);
        del_cus_page_gp.setHgap(50);

        //Back button
        Button backBt_delCusPage = new Button("Back");

        Image backImage_delCusPage = new Image("back.png");
        ImageView backImageView_delCusPage = new ImageView(backImage_delCusPage);
        backImageView_delCusPage.setFitWidth(30);
        backImageView_delCusPage.setFitHeight(30);

        backBt_delCusPage.setGraphic(backImageView_delCusPage);
        backBt_delCusPage.setGraphicTextGap(15);

        //Find button

        Button findBt_delCusPage = new Button("Find");

        Image findImage_delCusPage = new Image("search.png");
        ImageView findImageView_delCusPage = new ImageView(findImage_delCusPage);
        findBt_delCusPage.getStylesheets().add("findButtonStyle.css");
        findImageView_delCusPage.setFitWidth(30);
        findImageView_delCusPage.setFitHeight(30);

        findBt_delCusPage.setGraphic(findImageView_delCusPage);
        findBt_delCusPage.setGraphicTextGap(15);

        //Delete button

        Button delBt_delCusPage = new Button("Delete");

        Image delImage_delCusPage = new Image("delete.png");
        ImageView delImageView_delCusPage = new ImageView(delImage_delCusPage);
        delBt_delCusPage.getStylesheets().add("deleteButtonStyle.css");
        delImageView_delCusPage.setFitWidth(30);
        delImageView_delCusPage.setFitHeight(30);

        delBt_delCusPage.setGraphic(delImageView_delCusPage);
        delBt_delCusPage.setGraphicTextGap(15);


        //Container for the three buttons

        HBox btContainer_delPage = new HBox();
        btContainer_delPage.setAlignment(Pos.CENTER);
        btContainer_delPage.setSpacing(100);
        btContainer_delPage.getChildren().addAll(backBt_delCusPage,findBt_delCusPage,delBt_delCusPage);

        // adding buttons to the Scene (del page)
        delCusPane.setBottom(btContainer_delPage);
        BorderPane.setAlignment(btContainer_delPage,Pos.CENTER);

        //actions for buttons

        backBt_delCusPage.setOnAction(e-> {
            stage.setScene(customerPage);

            cusIdIn_delPage.clear();
            cusNameIn_delPage.clear();
            cusAddressIn_delPage.clear();
            cusMobileIn_delPage.clear();
            cusPlanIn_delPage.clear();

        });

        findBt_delCusPage.setOnAction(e->{
            String id = cusIdIn_delPage.getText().trim();
            int index = searchCusById(id);
            if(index >=0){
                cusNameIn_delPage.setText(mediaRental.getCustomers().get(index).getName());
                cusAddressIn_delPage.setText(mediaRental.getCustomers().get(index).getAddress());
                cusMobileIn_delPage.setText(mediaRental.getCustomers().get(index).getMobileNum());
                cusPlanIn_delPage.setText(mediaRental.getCustomers().get(index).getPlan());
            }else {
                showAlert("Error","Customer Not Found ",1);
            }

            cusIdIn_delPage.setOnKeyTyped(event->{      //clear text field when the id changed after getting the result
                cusNameIn_delPage.clear();
                cusAddressIn_delPage.clear();
                cusMobileIn_delPage.clear();
                cusPlanIn_delPage.clear();
            });

        });

        delBt_delCusPage.setOnAction(e->{
            String id = cusIdIn_delPage.getText().trim();
            int index = searchCusById(id);
            if (index >=0){
                for (int i = 0; i < mediaRental.getCustomers().get(index).getRentedMediaList().size() ; i++) {
                    mediaRental.returnMedia(id,mediaRental.getCustomers().get(index).getRentedMediaList().get(i));
                }
                mediaRental.getCustomers().remove(index);
                cusIdIn_delPage.clear();
                cusNameIn_delPage.clear();
                cusAddressIn_delPage.clear();
                cusMobileIn_delPage.clear();
                cusPlanIn_delPage.clear();

                updateAndSaveCustomer();
                updateAndSaveRentedAndCart();
                showAlert("Success","The Customer deleted Successfully",0);

            }else {
                showAlert("Error","Customer Not Found ",1);
            }

        });

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Customer update page ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        updateCusPane.setPadding(new Insets(50,50,50,50));
        updateCusPage.getStylesheets().add("mainPageStyle.css");

        //page labels and textField and toggle group
        Label cusIDLabel_UpdPage =new Label("Customer ID");
        cusIDLabel_UpdPage.setAlignment(Pos.CENTER);
        TextField cusIdIn_UpdPage = new TextField();
        cusIdIn_UpdPage.setPromptText("Enter Customer ID");
        cusIdIn_UpdPage.setAlignment(Pos.CENTER);


        Label cusNameLabel_UpdPage =new Label("Customer Name");
        cusNameLabel_UpdPage.setAlignment(Pos.CENTER);
        TextField cusNameIn_UpdPage = new TextField();
        cusNameIn_UpdPage.setAlignment(Pos.CENTER);
        cusNameIn_UpdPage.setEditable(false);


        Label cusAddressLabel_UpdPage =new Label("Customer Address");
        cusAddressLabel_UpdPage.setAlignment(Pos.CENTER);

        TextField cusAddressIn_UpdPage = new TextField();
        cusAddressIn_UpdPage.setAlignment(Pos.CENTER);
        cusAddressIn_UpdPage.setEditable(false);



        Label cusMobileLabel_UpdPage =new Label("Customer Mobile");
        cusMobileLabel_UpdPage.setAlignment(Pos.CENTER);

        TextField cusMobileIn_UpdPage = new TextField();
        cusMobileIn_UpdPage.setAlignment(Pos.CENTER);
        cusMobileIn_UpdPage.setEditable(false);



        //label for the plan
        Label planSettingLabel_Upd = new Label("Plan : ");
        planSettingLabel_Upd.setAlignment(Pos.CENTER);
        //toggle group for the plan (2 radio buttons)

        ToggleGroup planSettings_Upd = new ToggleGroup();

        RadioButton rb1_Upd = new RadioButton("Limited");
        rb1_Upd.setToggleGroup(planSettings_Upd);
        rb1_Upd.setDisable(true);

        RadioButton rb2_Upd = new RadioButton("Unlimited");
        rb2_Upd.setToggleGroup(planSettings_Upd);
        rb2_Upd.setDisable(true);

        HBox rBContainer_2 = new HBox();
        rBContainer_2.setAlignment(Pos.CENTER);
        rBContainer_2.setSpacing(30);
        rBContainer_2.getChildren().addAll(rb1_Upd,rb2_Upd);



        GridPane upd_cus_page_gp = new GridPane();

        upd_cus_page_gp.add(cusIDLabel_UpdPage,0,0);
        upd_cus_page_gp.add(cusIdIn_UpdPage,1,0);

        upd_cus_page_gp.add(cusNameLabel_UpdPage,0,1);
        upd_cus_page_gp.add(cusNameIn_UpdPage,1,1);

        upd_cus_page_gp.add(cusAddressLabel_UpdPage,0,2);
        upd_cus_page_gp.add(cusAddressIn_UpdPage,1,2);

        upd_cus_page_gp.add(cusMobileLabel_UpdPage,0,3);
        upd_cus_page_gp.add(cusMobileIn_UpdPage,1,3);

        upd_cus_page_gp.add(planSettingLabel_Upd,0,4);
        upd_cus_page_gp.add(rBContainer_2,1,4);


        updateCusPane.setCenter(upd_cus_page_gp);

        upd_cus_page_gp.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(upd_cus_page_gp,Pos.CENTER);
        upd_cus_page_gp.setVgap(15);
        upd_cus_page_gp.setHgap(50);

        //Back button
        Button backBt_UpdCusPage = new Button("Back");

        Image backImage_UpdCusPage = new Image("back.png");
        ImageView backImageView_UpdCusPage = new ImageView(backImage_UpdCusPage);
        backImageView_UpdCusPage.setFitWidth(30);
        backImageView_UpdCusPage.setFitHeight(30);

        backBt_UpdCusPage.setGraphic(backImageView_UpdCusPage);
        backBt_UpdCusPage.setGraphicTextGap(15);

        //Find button

        Button findBt_UpdCusPage = new Button("Find");

        Image findImage_UpdCusPage = new Image("search.png");
        ImageView findImageView_UpdCusPage = new ImageView(findImage_UpdCusPage);
        findBt_UpdCusPage.getStylesheets().add("findButtonStyle.css");
        findImageView_UpdCusPage.setFitWidth(30);
        findImageView_UpdCusPage.setFitHeight(30);

        findBt_UpdCusPage.setGraphic(findImageView_UpdCusPage);
        findBt_UpdCusPage.setGraphicTextGap(15);

        //Update Button

        Button UpdBt_UpdCusPage = new Button("Update");

        Image UpdImage_UpdCusPage = new Image("update.png");
        ImageView UpdImageView_UpdCusPage = new ImageView(UpdImage_UpdCusPage);
        UpdBt_UpdCusPage.getStylesheets().add("addButtonStyle.css");
        UpdImageView_UpdCusPage.setFitWidth(30);
        UpdImageView_UpdCusPage.setFitHeight(30);

        UpdBt_UpdCusPage.setGraphic(UpdImageView_UpdCusPage);
        UpdBt_UpdCusPage.setGraphicTextGap(15);


        //container for the lower buttons

        HBox btContainer_UpdPage = new HBox();
        btContainer_UpdPage.setAlignment(Pos.CENTER);
        btContainer_UpdPage.setSpacing(100);
        btContainer_UpdPage.getChildren().addAll(backBt_UpdCusPage,findBt_UpdCusPage,UpdBt_UpdCusPage);

        // adding buttons to the Scene (Update page)
        updateCusPane.setBottom(btContainer_UpdPage);
        BorderPane.setAlignment(btContainer_UpdPage,Pos.CENTER);

        //actions for the buttons
        backBt_UpdCusPage.setOnAction(e-> {
            stage.setScene(customerPage);

            rb1_Upd.setSelected(false);      //reset the text field and buttons
            rb2_Upd.setSelected(false);
            cusIdIn_UpdPage.clear();
            cusNameIn_UpdPage.clear();
            cusAddressIn_UpdPage.clear();
            cusMobileIn_UpdPage.clear();
            rb1_Upd.setDisable(true);
            rb2_Upd.setDisable(true);
            cusNameIn_UpdPage.setEditable(false);
            cusAddressIn_UpdPage.setEditable(false);
            cusMobileIn_UpdPage.setEditable(false);

        });

        findBt_UpdCusPage.setOnAction(e->{
            String id = cusIdIn_UpdPage.getText();
            int index = searchCusById(id);
            if(index >=0){
                cusIdIn_UpdPage.setEditable(true);
                cusNameIn_UpdPage.setEditable(true);
                cusAddressIn_UpdPage.setEditable(true);
                cusMobileIn_UpdPage.setEditable(true);
                rb1_Upd.setDisable(false);
                rb2_Upd.setDisable(false);

                cusNameIn_UpdPage.setText(mediaRental.getCustomers().get(index).getName());
                cusAddressIn_UpdPage.setText(mediaRental.getCustomers().get(index).getAddress());
                cusMobileIn_UpdPage.setText(mediaRental.getCustomers().get(index).getMobileNum());
                if (mediaRental.getCustomers().get(index).getPlan().equalsIgnoreCase("limited")){
                    rb1_Upd.setSelected(true);
                }else {
                    rb2_Upd.setSelected(true);
                }
            }else {
                showAlert("Error","Customer Not Found ",1);
            }

            cusIdIn_UpdPage.setOnKeyTyped(event->{      //clear text field when the id changed after getting the result
                rb1_Upd.setSelected(false);
                rb2_Upd.setSelected(false);
                cusNameIn_UpdPage.clear();
                cusAddressIn_UpdPage.clear();
                cusMobileIn_UpdPage.clear();
                rb1_Upd.setDisable(true);
                rb2_Upd.setDisable(true);
                cusNameIn_UpdPage.setEditable(false);
                cusAddressIn_UpdPage.setEditable(false);
                cusMobileIn_UpdPage.setEditable(false);
            });


        });


        UpdBt_UpdCusPage.setOnAction(e->{

            if(cusIdIn_UpdPage.getText().trim().matches("[0-9]+") && !cusNameIn_UpdPage.getText().matches(".*\\d.*") && cusMobileIn_UpdPage.getText().trim().matches("[0-9]+") ) {
                    String id = cusIdIn_UpdPage.getText().trim();
                    String name = cusNameIn_UpdPage.getText().trim();
                    String address = cusAddressIn_UpdPage.getText().trim();
                    String mobileNum = cusMobileIn_UpdPage.getText().trim();
                    String plan;
                    if (rb1_Upd.isSelected()) {
                        plan = "LIMITED";

                    } else {
                        plan = "UNLIMITED";
                    }

                    int index = searchCusById(id);
                    if (index >= 0) {
                        mediaRental.getCustomers().get(index).setName(name);
                        mediaRental.getCustomers().get(index).setAddress(address);
                        mediaRental.getCustomers().get(index).setMobileNum(mobileNum);
                        mediaRental.getCustomers().get(index).setPlan(plan);
                    }

                cusNameIn_UpdPage.setEditable(false);
                cusAddressIn_UpdPage.setEditable(false);
                cusMobileIn_UpdPage.setEditable(false);
                rb1_Upd.setDisable(true);
                rb2_Upd.setDisable(true);

                updateAndSaveCustomer();
                updateAndSaveRentedAndCart();
                showAlert("Success","The Customer Updated Successfully",0);
            }
            else {
                showAlert("Error","Please enter correct data ",1);
            }


        });


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Customer search page ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        searchCusPane.setPadding(new Insets(50,50,50,50));
        searchCusPage.getStylesheets().add("mainPageStyle.css");


        Label cusIDLabel_searchPage =new Label("Customer ID");
        cusIDLabel_searchPage.setAlignment(Pos.CENTER);
        TextField cusIdIn_searchPage = new TextField();
        cusIdIn_searchPage.setPromptText("Enter Customer ID");
        cusIdIn_searchPage.setAlignment(Pos.CENTER);


        Label cusNameLabel_search =new Label("Customer Name");
        cusNameLabel_search.setAlignment(Pos.CENTER);
        TextField cusNameIn_searchPage = new TextField();
        cusNameIn_searchPage.setAlignment(Pos.CENTER);
        cusNameIn_searchPage.setEditable(false);

        Label cusAddressLabel_searchPage =new Label("Customer Address");
        cusAddressLabel_searchPage.setAlignment(Pos.CENTER);

        TextField cusAddressIn_searchPage = new TextField();
        cusAddressIn_searchPage.setAlignment(Pos.CENTER);
        cusAddressIn_searchPage.setEditable(false);

        Label cusMobileLabel_searchPage =new Label("Customer Mobile");
        cusMobileLabel_searchPage.setAlignment(Pos.CENTER);

        TextField cusMobileIn_searchPage = new TextField();
        cusMobileIn_searchPage.setAlignment(Pos.CENTER);
        cusMobileIn_searchPage.setEditable(false);

        Label cusPlanLabel_searchPage =new Label("Customer Plan");
        cusPlanLabel_searchPage.setAlignment(Pos.CENTER);

        TextField cusPlanIn_searchPage = new TextField();
        cusPlanIn_searchPage.setAlignment(Pos.CENTER);
        cusPlanIn_searchPage.setEditable(false);


        GridPane ser_cus_page_gp = new GridPane();

        ser_cus_page_gp.add(cusIDLabel_searchPage,0,0);
        ser_cus_page_gp.add(cusIdIn_searchPage,1,0);

        ser_cus_page_gp.add(cusNameLabel_search,0,1);
        ser_cus_page_gp.add(cusNameIn_searchPage,1,1);

        ser_cus_page_gp.add(cusAddressLabel_searchPage,0,2);
        ser_cus_page_gp.add(cusAddressIn_searchPage,1,2);

        ser_cus_page_gp.add(cusMobileLabel_searchPage,0,3);
        ser_cus_page_gp.add(cusMobileIn_searchPage,1,3);

        ser_cus_page_gp.add(cusPlanLabel_searchPage,0,4);
        ser_cus_page_gp.add(cusPlanIn_searchPage,1,4);

        searchCusPane.setCenter(ser_cus_page_gp);

        ser_cus_page_gp.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(ser_cus_page_gp,Pos.CENTER);
        ser_cus_page_gp.setVgap(15);
        ser_cus_page_gp.setHgap(50);

        //Back button
        Button backBt_serCusPage = new Button("Back");

        Image backImage_serCusPage = new Image("back.png");
        ImageView backImageView_serCusPage = new ImageView(backImage_serCusPage);
        backImageView_serCusPage.setFitWidth(30);
        backImageView_serCusPage.setFitHeight(30);

        backBt_serCusPage.setGraphic(backImageView_serCusPage);
        backBt_serCusPage.setGraphicTextGap(15);

        //Find button

        Button findBt_serCusPage = new Button("Find");

        Image findImage_serCusPage = new Image("search.png");
        ImageView findImageView_serCusPage = new ImageView(findImage_serCusPage);
        findBt_serCusPage.getStylesheets().add("findButtonStyle.css");
        findImageView_serCusPage.setFitWidth(30);
        findImageView_serCusPage.setFitHeight(30);

        findBt_serCusPage.setGraphic(findImageView_serCusPage);
        findBt_serCusPage.setGraphicTextGap(15);

        //container for the lower buttons
        HBox btContainer_serPage = new HBox();
        btContainer_serPage.setAlignment(Pos.CENTER);
        btContainer_serPage.setSpacing(100);
        btContainer_serPage.getChildren().addAll(backBt_serCusPage,findBt_serCusPage);

        // adding buttons to the Scene (Update page)
        searchCusPane.setBottom(btContainer_serPage);
        BorderPane.setAlignment(btContainer_serPage,Pos.CENTER);


        //Action buttons

        backBt_serCusPage.setOnAction(e-> {
            stage.setScene(customerPage);
            cusIdIn_searchPage.clear();
            cusNameIn_searchPage.clear();
            cusAddressIn_searchPage.clear();
            cusMobileIn_searchPage.clear();
            cusPlanIn_searchPage.clear();

        });
        findBt_serCusPage.setOnAction(e->{

            String id = cusIdIn_searchPage.getText().trim();
            int index = searchCusById(id);
            if(index >=0){
                cusNameIn_searchPage.setText(mediaRental.getCustomers().get(index).getName());
                cusAddressIn_searchPage.setText(mediaRental.getCustomers().get(index).getAddress());
                cusMobileIn_searchPage.setText(mediaRental.getCustomers().get(index).getMobileNum());
                cusPlanIn_searchPage.setText(mediaRental.getCustomers().get(index).getPlan());
            }else {
                showAlert("Error","Customer Not Found ",1);
            }
            cusIdIn_searchPage.setOnKeyTyped(event->{      //clear text field when the id changed after getting the result
                cusNameIn_searchPage.clear();
                cusAddressIn_searchPage.clear();
                cusMobileIn_searchPage.clear();
                cusPlanIn_searchPage.clear();
            });
        });

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Media Pages ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        mediaPane.setPadding(new Insets(50,50,50,50));
        mediaPage.getStylesheets().add("mainPageStyle.css");

        //buttons in Media page
        Button mediaB1 = new Button("Add New Media");
        Button mediaB2 = new Button("Delete Media");
        Button mediaB3 = new Button("Update Information about Media");
        Button mediaB4 = new Button("Search a Media by code");
        Button mediaB5 = new Button("Print All Media information");

        VBox mediaMenu= new VBox();
        mediaMenu.setSpacing(25);
        mediaMenu.setPadding(new Insets(30,30,30,30));
        mediaMenu.getChildren().addAll(mediaB1,mediaB2,mediaB3,mediaB4,mediaB5);
        mediaMenu.setAlignment(Pos.CENTER);
        mediaPane.setCenter(mediaMenu);
        BorderPane.setAlignment(mediaMenu,Pos.CENTER);


        // BACK buttons
        Button backBt2 = new Button("Return To Main Page");

        Image backImage2 = new Image("back.png");
        ImageView backImageView2 = new ImageView(backImage2);
        backImageView2.setFitWidth(30);
        backImageView2.setFitHeight(30);

        backBt2.setGraphic(backImageView2);
        backBt2.setGraphicTextGap(15);

        mediaPane.setBottom(backBt2);
        BorderPane.setAlignment(backBt2,Pos.CENTER);

        backBt2.setOnAction(e -> stage.setScene(mainPage) );  //Actions for the buttons

        mediaB1.setOnAction(e->stage.setScene(addMediaPage));
        mediaB2.setOnAction(e->stage.setScene(delMediaPage));
        mediaB3.setOnAction(e-> stage.setScene(updateMediaPage));
        mediaB4.setOnAction(e->stage.setScene(searchMediaPage));
        mediaB5.setOnAction(e->stage.setScene(printMediaPage));


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Add Media Page ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        addMediaPane.setPadding(new Insets(50,50,50,50));
        addMediaPage.getStylesheets().add("mainPageStyle.css");

        //combo box for choosing media type
        ComboBox<String> mediaComboBox = new ComboBox<>();
        mediaComboBox.getItems().addAll("Album","Game","Movie");
        mediaComboBox.setPromptText("Choose Media Type");

        //adding the combo box to the top of the page
        addMediaPane.setTop(mediaComboBox);
        BorderPane.setAlignment(mediaComboBox,Pos.BOTTOM_CENTER);

        //Label and text box to enter media properties
        Label mediaCodeLabel_add = new Label("Media Code");
        mediaCodeLabel_add.setAlignment(Pos.CENTER);
        TextField mediaCodeText_add = new TextField();
        mediaCodeText_add.setPromptText("Enter the Media code");
        mediaCodeText_add.setDisable(true);
        mediaCodeText_add.setAlignment(Pos.CENTER);

        Label titleLabel_add = new Label("Title ");
        titleLabel_add.setAlignment(Pos.CENTER);
        TextField titleText_add = new TextField();
        titleText_add.setPromptText("Enter the media Title");
        titleText_add.setDisable(true);     //inactive until the media type was chosen
        titleText_add.setAlignment(Pos.CENTER);

        Label numOfCopies_add = new Label("Number Of Copies");
        numOfCopies_add.setAlignment(Pos.CENTER);
        TextField numOfCopiesIn_add = new TextField();
        numOfCopiesIn_add.setPromptText("Enter Number Of Copies");
        numOfCopiesIn_add.setDisable(true);
        numOfCopiesIn_add.setAlignment(Pos.CENTER);

        //---- album ----
        Label artistName_add = new Label("Artist Name");
        artistName_add.setVisible(false);  //setting them to invisible and inactive until you chose media type
        artistName_add.setAlignment(Pos.CENTER);

        TextField artistNameText_add = new TextField();
        artistNameText_add.setPromptText("Enter the Artist Name");
        artistNameText_add.setVisible(false);
        artistNameText_add.setDisable(true);
        artistNameText_add.setAlignment(Pos.CENTER);

        Label songList_add = new Label("Song List");
        songList_add.setVisible(false);
        songList_add.setAlignment(Pos.CENTER);
        TextField songListText_add = new TextField();
        songListText_add.setPromptText("Songs seperated by (,)");
        songListText_add.setVisible(false);
        songListText_add.setDisable(true);
        songListText_add.setAlignment(Pos.CENTER);

        //---- Movie ----

        Label rating_add = new Label("Rating");
        rating_add.setVisible(false);
        rating_add.setAlignment(Pos.CENTER);

        ToggleGroup ratingGroup = new ToggleGroup();

        RadioButton hr_add = new RadioButton("HR");
        hr_add.setAlignment(Pos.CENTER);
        hr_add.setToggleGroup(ratingGroup);
        hr_add.setDisable(true);


        RadioButton dr_add = new RadioButton("DR");
        dr_add.setAlignment(Pos.CENTER);
        dr_add.setToggleGroup(ratingGroup);
        dr_add.setDisable(true);


        RadioButton ac_add = new RadioButton("AC");
        ac_add.setAlignment(Pos.CENTER);
        ac_add.setToggleGroup(ratingGroup);
        ac_add.setDisable(true);

        HBox rating_co_add = new HBox();
        rating_co_add.setAlignment(Pos.CENTER);
        rating_co_add.setSpacing(30);
        rating_co_add.getChildren().addAll(hr_add,dr_add,ac_add);
        rating_co_add.setVisible(false);

        //---- Game ----
        Label gameWeight_add = new Label("Weight");
        gameWeight_add.setAlignment(Pos.CENTER);
        gameWeight_add.setVisible(false);
        TextField gameWeightText_add = new TextField();
        gameWeightText_add.setPromptText("Enter the weight");
        gameWeightText_add.setAlignment(Pos.CENTER);
        gameWeightText_add.setVisible(false);
        gameWeightText_add.setDisable(true);

        GridPane media_add_gp = new GridPane();


        media_add_gp.add(mediaCodeLabel_add,0,0);
        media_add_gp.add(mediaCodeText_add,1,0);

        media_add_gp.add(titleLabel_add,0,1);
        media_add_gp.add(titleText_add,1,1);

        media_add_gp.add(numOfCopies_add,0,2);
        media_add_gp.add(numOfCopiesIn_add,1,2);

        //---- album ----
        media_add_gp.add(artistName_add,0,3);
        media_add_gp.add(artistNameText_add,1,3);

        media_add_gp.add(songList_add,0,4);
        media_add_gp.add(songListText_add,1,4);
        //---------------

        //---- Movie ----
        media_add_gp.add(rating_add,0,3);
        media_add_gp.add(rating_co_add,1,3);
        //---------------

        //---- Game -----
        media_add_gp.add(gameWeight_add,0,3);
        media_add_gp.add(gameWeightText_add,1,3);
        //---------------

        //adding th grid pane to the scene
        addMediaPane.setCenter(media_add_gp);
        media_add_gp.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(media_add_gp,Pos.CENTER);
        media_add_gp.setVgap(15);
        media_add_gp.setHgap(50);

        //action for the combo box
        mediaComboBox.setOnAction(e->{


            if(mediaComboBox.getValue().equalsIgnoreCase("Album")){
                mediaCodeText_add.setDisable(false);

                mediaCodeText_add.clear();
                titleText_add.clear();
                numOfCopiesIn_add.clear();
                artistNameText_add.clear();
                songListText_add.clear();
                hr_add.setSelected(false);
                ac_add.setSelected(false);
                dr_add.setSelected(false);
                gameWeightText_add.clear();



                artistName_add.setVisible(true);
                artistNameText_add.setVisible(true);
                songList_add.setVisible(true);
                songListText_add.setVisible(true);

                rating_add.setVisible(false);
                rating_co_add.setVisible(false);
                gameWeight_add.setVisible(false);
                gameWeightText_add.setVisible(false);

                //activating next input when you enter the previous input correctly
                mediaCodeText_add.setOnKeyReleased(event->titleText_add.setDisable(mediaCodeText_add.getText().trim().isEmpty() || !mediaCodeText_add.getText().trim().matches("[0-9]+")));
                titleText_add.setOnKeyReleased(event -> numOfCopiesIn_add.setDisable(titleText_add.getText().trim().isEmpty()));
                numOfCopiesIn_add.setOnKeyReleased(event -> artistNameText_add.setDisable(numOfCopiesIn_add.getText().trim().isEmpty() || !numOfCopiesIn_add.getText().trim().matches("[0-9]+")));
                artistNameText_add.setOnKeyReleased(event -> songListText_add.setDisable(artistName_add.getText().trim().isEmpty() ));


            }else if (mediaComboBox.getValue().equalsIgnoreCase("Movie")){
                mediaCodeText_add.setDisable(false);

                mediaCodeText_add.clear();
                titleText_add.clear();
                numOfCopiesIn_add.clear();
                artistNameText_add.clear();
                songListText_add.clear();
                hr_add.setSelected(false);
                ac_add.setSelected(false);
                dr_add.setSelected(false);
                gameWeightText_add.clear();



                rating_add.setVisible(true);
                rating_co_add.setVisible(true);

                artistName_add.setVisible(false);
                artistNameText_add.setVisible(false);
                songList_add.setVisible(false);
                songListText_add.setVisible(false);
                gameWeight_add.setVisible(false);
                gameWeightText_add.setVisible(false);

                //activating next input when you enter the previous input correctly
                mediaCodeText_add.setOnKeyReleased(event->titleText_add.setDisable(mediaCodeText_add.getText().trim().isEmpty() || !mediaCodeText_add.getText().trim().matches("[0-9]+")));
                titleText_add.setOnKeyReleased(event -> numOfCopiesIn_add.setDisable(titleText_add.getText().trim().isEmpty()));
                numOfCopiesIn_add.setOnKeyReleased(event -> {
                    boolean state = numOfCopiesIn_add.getText().trim().matches("[0-9]+");
                    hr_add.setDisable(!state);
                    ac_add.setDisable(!state);
                    dr_add.setDisable(!state);
                });


            }else if (mediaComboBox.getValue().equalsIgnoreCase("Game")){
                mediaCodeText_add.setDisable(false);

                mediaCodeText_add.clear();
                titleText_add.clear();
                numOfCopiesIn_add.clear();
                artistNameText_add.clear();
                songListText_add.clear();
                hr_add.setSelected(false);
                ac_add.setSelected(false);
                dr_add.setSelected(false);
                gameWeightText_add.clear();

                gameWeight_add.setVisible(true);
                gameWeightText_add.setVisible(true);

                rating_add.setVisible(false);
                rating_co_add.setVisible(false);
                artistName_add.setVisible(false);
                artistNameText_add.setVisible(false);
                songList_add.setVisible(false);
                songListText_add.setVisible(false);

                //activating next input when you enter the previous input correctly
                mediaCodeText_add.setOnKeyReleased(event->titleText_add.setDisable(mediaCodeText_add.getText().trim().isEmpty() || !mediaCodeText_add.getText().trim().matches("[0-9]+")));
                titleText_add.setOnKeyReleased(event -> numOfCopiesIn_add.setDisable(titleText_add.getText().trim().isEmpty()));
                numOfCopiesIn_add.setOnKeyReleased(event -> gameWeightText_add.setDisable(numOfCopiesIn_add.getText().trim().isEmpty() || !numOfCopiesIn_add.getText().trim().matches("[0-9]+")));


            }
        });


        //add back button
        Button backBt_addMedia = new Button("Back");

        Image backImage_addMedia = new Image("back.png");
        ImageView backImageView_addMedia = new ImageView(backImage_addMedia);
        backImageView_addMedia.setFitWidth(30);
        backImageView_addMedia.setFitHeight(30);

        backBt_addMedia.setGraphic(backImageView_addMedia);
        backBt_addMedia.setGraphicTextGap(15);

        Button addBT_addMedia = new Button("Add");
        Image addImage_addMedia = new Image("plus.png");
        ImageView addImageView_addMedia = new ImageView(addImage_addMedia);
        addBT_addMedia.getStylesheets().add("addButtonStyle.css");
        addImageView_addMedia.setFitWidth(30);
        addImageView_addMedia.setFitHeight(30);

        addBT_addMedia.setGraphic(addImageView_addMedia);
        addBT_addMedia.setGraphicTextGap(15);

        HBox backAddContainer_addMedia = new HBox();
        backAddContainer_addMedia.setAlignment(Pos.CENTER);
        backAddContainer_addMedia.setSpacing(100);
        backAddContainer_addMedia.getChildren().addAll(backBt_addMedia,addBT_addMedia);

        addMediaPane.setBottom(backAddContainer_addMedia);
        BorderPane.setAlignment(backAddContainer_addMedia,Pos.CENTER);

        //buttons action
        backBt_addMedia.setOnAction(e-> {
            stage.setScene(mediaPage);
            mediaCodeText_add.clear();
            titleText_add.clear();
            titleText_add.setDisable(true);
            numOfCopiesIn_add.clear();
            numOfCopiesIn_add.setDisable(true);
            artistNameText_add.clear();
            artistNameText_add.setDisable(true);
            songListText_add.clear();
            songListText_add.setDisable(true);
            hr_add.setSelected(false);
            hr_add.setDisable(true);
            ac_add.setSelected(false);
            ac_add.setDisable(true);
            dr_add.setSelected(false);
            dr_add.setDisable(true);
            gameWeightText_add.clear();
            gameWeightText_add.setDisable(true);

        });

        addBT_addMedia.setOnAction(e->{
            if(mediaCodeText_add.getText().trim().matches("[0-9]+") && numOfCopiesIn_add.getText().trim().matches("[0-9]+") ){
                if(mediaComboBox.getValue().equalsIgnoreCase("Movie")){
                    String code = mediaCodeText_add.getText();
                    String title = titleText_add.getText();
                    String numOfCopies = numOfCopiesIn_add.getText();

                    String rating;
                    if (hr_add.isSelected()){
                        rating = "hr";
                    }else if(ac_add.isSelected()) {
                        rating = "ac";
                    }else {
                        rating = "dr";
                    }

                    try{
                        mediaRental.addMovie(code,title,Integer.parseInt(numOfCopies.trim()),rating);
                        updateAndSaveMedia();    //saving data
                        showAlert("Success","The Media added Successfully",0);
                    }catch (Exception exception){
                        showAlert("Error",exception.getMessage(),1);
                    }
                }else if (mediaComboBox.getValue().equalsIgnoreCase("Game")){
                    String code = mediaCodeText_add.getText();
                    String title = titleText_add.getText();
                    String numOfCopies = numOfCopiesIn_add.getText();
                    String weight = gameWeightText_add.getText();

                    try{
                        mediaRental.addGame(code,title,Integer.parseInt(numOfCopies.trim()),Double.parseDouble(weight.trim()));
                        updateAndSaveMedia();  //saving
                        showAlert("Success","The Media added Successfully",0);
                    }catch (Exception exception){
                        showAlert("Error",exception.getMessage(),1);
                    }

                }else if (mediaComboBox.getValue().equalsIgnoreCase("Album")){
                    String code = mediaCodeText_add.getText();
                    String title = titleText_add.getText();
                    String numOfCopies = numOfCopiesIn_add.getText();
                    String artistName = artistNameText_add.getText();
                    String songList = songListText_add.getText();
                    try{
                        mediaRental.addAlbum(code,title,Integer.parseInt(numOfCopies),artistName.trim(),songList.trim());
                        updateAndSaveMedia();
                        showAlert("Success","The Media added Successfully",0);
                    }catch (Exception exception){
                        showAlert("Error",exception.getMessage(),1);
                    }

                }
            }else {
                showAlert("Error","Please enter correct data ",1);
            }


        });

        mediaCodeText_add.setOnKeyTyped(event->{      //clear text field when the code changed
            titleText_add.clear();
            titleText_add.setDisable(true);
            numOfCopiesIn_add.clear();
            numOfCopiesIn_add.setDisable(true);
            artistNameText_add.clear();
            artistNameText_add.setDisable(true);
            songListText_add.clear();
            songListText_add.setDisable(true);
            hr_add.setSelected(false);
            hr_add.setDisable(true);
            ac_add.setSelected(false);
            ac_add.setDisable(true);
            dr_add.setSelected(false);
            dr_add.setDisable(true);
            gameWeightText_add.clear();
            gameWeightText_add.setDisable(true);
        });


        numOfCopiesIn_add.setOnKeyTyped(event -> {

            if(!(mediaCodeText_add.getText().trim().matches("[0-9]+")&& numOfCopiesIn_add.getText().trim().matches("[0-9]+"))) {

                artistNameText_add.clear();
                artistNameText_add.setDisable(true);
                songListText_add.clear();
                songListText_add.setDisable(true);
                hr_add.setSelected(false);
                hr_add.setDisable(true);
                ac_add.setSelected(false);
                ac_add.setDisable(true);
                dr_add.setSelected(false);
                dr_add.setDisable(true);
                gameWeightText_add.clear();
                gameWeightText_add.setDisable(true);

            }
        });




    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Delete Media Page ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


        delMediaPane.setPadding(new Insets(50,50,50,50));
        delMediaPage.getStylesheets().add("mainPageStyle.css");

        //page labels and textField and toggle group
        Label mediaCode_delPage =new Label("Media Code");
        mediaCode_delPage.setAlignment(Pos.CENTER);
        TextField mediaCodeText_delPage = new TextField();
        mediaCodeText_delPage.setPromptText("Enter Media Code");
        mediaCodeText_delPage.setAlignment(Pos.CENTER);


        Label mediaTitle_delPage =new Label("Media Title");
        mediaTitle_delPage.setAlignment(Pos.CENTER);
        TextField mediaTitleText_delPage = new TextField();
        mediaTitleText_delPage.setAlignment(Pos.CENTER);
        mediaTitleText_delPage.setEditable(false);

        Label numOfCopies_delPage =new Label("Number Of Copies");
        numOfCopies_delPage.setAlignment(Pos.CENTER);

        TextField numOfCopiesText_delPage = new TextField();
        numOfCopiesText_delPage.setAlignment(Pos.CENTER);
        numOfCopiesText_delPage.setEditable(false);


        //---- Album ----
        Label artistName_del = new Label("Artist Name");
        artistName_del.setVisible(false);  //setting them to invisible and inactive until you chose media type
        artistName_del.setAlignment(Pos.CENTER);

        TextField artistNameText_del = new TextField();
        artistNameText_del.setVisible(false);
        artistNameText_del.setAlignment(Pos.CENTER);
        artistNameText_del.setEditable(false);

        Label songList_del = new Label("Song List");
        songList_del.setVisible(false);
        songList_del.setAlignment(Pos.CENTER);

        TextField songListText_del = new TextField();
        songListText_del.setVisible(false);
        songListText_del.setAlignment(Pos.CENTER);
        songListText_del.setEditable(false);

        //---- Movie ----
        Label rating_del = new Label("Rating");
        rating_del.setVisible(false);
        rating_del.setAlignment(Pos.CENTER);

        TextField ratingText_del = new TextField();
        ratingText_del.setVisible(false);
        ratingText_del.setAlignment(Pos.CENTER);
        ratingText_del.setEditable(false);

        //---- Game ----
        Label gameWeight_del = new Label("Weight");
        gameWeight_del.setAlignment(Pos.CENTER);
        gameWeight_del.setVisible(false);

        TextField gameWeightText_del = new TextField();
        gameWeightText_del.setAlignment(Pos.CENTER);
        gameWeightText_del.setVisible(false);
        gameWeightText_del.setEditable(false);





        GridPane delMedia_GP = new GridPane();

        delMedia_GP.add(mediaCode_delPage,0,0);
        delMedia_GP.add(mediaCodeText_delPage,1,0);

        delMedia_GP.add(mediaTitle_delPage,0,1);
        delMedia_GP.add(mediaTitleText_delPage,1,1);

        delMedia_GP.add(numOfCopies_delPage,0,2);
        delMedia_GP.add(numOfCopiesText_delPage,1,2);

        //---- Album ----
        delMedia_GP.add(artistName_del,0,3);
        delMedia_GP.add(artistNameText_del,1,3);

        delMedia_GP.add(songList_del,0,4);
        delMedia_GP.add(songListText_del,1,4);

        //---- Movie ----
        delMedia_GP.add(rating_del,0,3);
        delMedia_GP.add(ratingText_del,1,3);

        //---- Game ----
        delMedia_GP.add(gameWeight_del,0,3);
        delMedia_GP.add(gameWeightText_del,1,3);


        delMediaPane.setCenter(delMedia_GP);

        delMedia_GP.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(delMedia_GP,Pos.CENTER);
        delMedia_GP.setVgap(15);
        delMedia_GP.setHgap(50);

        //Back button
        Button backBt_delMediaPage = new Button("Back");

        Image backImage_delMediaPage = new Image("back.png");
        ImageView backImageView_delMediaPage = new ImageView(backImage_delMediaPage);
        backImageView_delMediaPage.setFitWidth(30);
        backImageView_delMediaPage.setFitHeight(30);

        backBt_delMediaPage.setGraphic(backImageView_delMediaPage);
        backBt_delMediaPage.setGraphicTextGap(15);

        //Find button

        Button findBt_delMediaPage = new Button("Find");

        Image findImage_delMediaPage = new Image("search.png");
        ImageView findImageView_delMediaPage = new ImageView(findImage_delMediaPage);
        findBt_delMediaPage.getStylesheets().add("findButtonStyle.css");
        findImageView_delMediaPage.setFitWidth(30);
        findImageView_delMediaPage.setFitHeight(30);

        findBt_delMediaPage.setGraphic(findImageView_delMediaPage);
        findBt_delMediaPage.setGraphicTextGap(15);

        //Delete button

        Button delBt_delMediaPage = new Button("Delete");

        Image delImage_delMediaPage = new Image("delete.png");
        ImageView delImageView_delMediaPage = new ImageView(delImage_delMediaPage);
        delBt_delMediaPage.getStylesheets().add("deleteButtonStyle.css");
        delImageView_delMediaPage.setFitWidth(30);
        delImageView_delMediaPage.setFitHeight(30);

        delBt_delMediaPage.setGraphic(delImageView_delMediaPage);
        delBt_delMediaPage.setGraphicTextGap(15);

        //Container for the three buttons

        HBox bt_delMediaPage_cont = new HBox();
        bt_delMediaPage_cont.setAlignment(Pos.CENTER);
        bt_delMediaPage_cont.setSpacing(100);
        bt_delMediaPage_cont.getChildren().addAll(backBt_delMediaPage,findBt_delMediaPage,delBt_delMediaPage);

        // adding buttons to the Scene (del page)
        delMediaPane.setBottom(bt_delMediaPage_cont);
        BorderPane.setAlignment(bt_delMediaPage_cont,Pos.CENTER);

        //actions for buttons

        backBt_delMediaPage.setOnAction(e-> {
            stage.setScene(mediaPage);

            mediaCodeText_delPage.clear();
            mediaTitleText_delPage.clear();
            numOfCopiesText_delPage.clear();

            artistName_del.setVisible(false);
            artistNameText_del.clear();
            artistNameText_del.setVisible(false);
            songList_del.setVisible(false);
            songListText_del.clear();
            songListText_del.setVisible(false);

            gameWeight_del.setVisible(false);
            gameWeightText_del.clear();
            gameWeightText_del.setVisible(false);

            rating_del.setVisible(false);
            ratingText_del.clear();
            ratingText_del.setVisible(false);

        });

        findBt_delMediaPage.setOnAction(e->{
            String code = mediaCodeText_delPage.getText().trim();
            int index = searchMediaByCode(code);

            if( index >=0 ){

                if(mediaRental.getMediaList().get(index) instanceof Game){  //test if it is movie or album or game

                    gameWeight_del.setVisible(true);
                    gameWeightText_del.setVisible(true);

                    rating_del.setVisible(false);
                    ratingText_del.clear();
                    ratingText_del.setVisible(false);
                    artistName_del.setVisible(false);
                    artistNameText_del.clear();
                    artistNameText_del.setVisible(false);
                    songList_del.setVisible(false);
                    songListText_del.clear();
                    songListText_del.setVisible(false);

                    mediaTitleText_delPage.setText(mediaRental.getMediaList().get(index).getTitle());          //view results depending on its type
                    numOfCopiesText_delPage.setText(mediaRental.getMediaList().get(index).getNumOfCopies() + "");
                    gameWeightText_del.setText(((Game) mediaRental.getMediaList().get(index)).getWeight() + "");


                }else if (mediaRental.getMediaList().get(index) instanceof Album){

                    artistName_del.setVisible(true);
                    artistNameText_del.setVisible(true);
                    songList_del.setVisible(true);
                    songListText_del.setVisible(true);

                    gameWeight_del.setVisible(false);
                    gameWeightText_del.clear();
                    gameWeightText_del.setVisible(false);
                    rating_del.setVisible(false);
                    ratingText_del.clear();
                    ratingText_del.setVisible(false);

                    mediaTitleText_delPage.setText(mediaRental.getMediaList().get(index).getTitle());
                    numOfCopiesText_delPage.setText(mediaRental.getMediaList().get(index).getNumOfCopies() + "");
                    artistNameText_del.setText(((Album) mediaRental.getMediaList().get(index)).getArtist());
                    songListText_del.setText(((Album) mediaRental.getMediaList().get(index)).getSongs().toString().trim());

                }else if (mediaRental.getMediaList().get(index) instanceof Movie){

                    rating_del.setVisible(true);
                    ratingText_del.setVisible(true);

                    gameWeight_del.setVisible(false);
                    gameWeightText_del.clear();
                    gameWeightText_del.setVisible(false);
                    artistName_del.setVisible(false);
                    artistNameText_del.clear();
                    artistNameText_del.setVisible(false);
                    songList_del.setVisible(false);
                    songListText_del.clear();
                    songListText_del.setVisible(false);

                    mediaTitleText_delPage.setText(mediaRental.getMediaList().get(index).getTitle());
                    numOfCopiesText_delPage.setText(mediaRental.getMediaList().get(index).getNumOfCopies() + "");
                    ratingText_del.setText(((Movie) mediaRental.getMediaList().get(index)).getRating());

                }

            }else if (index == -1){
                showAlert("Error","Media Not Found ",1);
            }

            mediaCodeText_delPage.setOnKeyTyped(event->{      //clear text field when the id changed after getting the result
                mediaTitleText_delPage.clear();
                numOfCopiesText_delPage.clear();
                gameWeightText_del.clear();
                artistNameText_del.clear();
                songListText_del.clear();
                ratingText_del.clear();

                artistName_del.setVisible(false);
                artistNameText_del.setVisible(false);
                songList_del.setVisible(false);
                songListText_del.setVisible(false);
                gameWeight_del.setVisible(false);
                gameWeightText_del.setVisible(false);
                rating_del.setVisible(false);
                ratingText_del.setVisible(false);



            });


        });

        delBt_delMediaPage.setOnAction(e->{
            String code = mediaCodeText_delPage.getText().trim();
            int index = searchMediaByCode(code);
            if (index >=0){
                for (int i = 0; i < mediaRental.getCustomers().size(); i++) {
                    mediaRental.getCustomers().get(i).getRentedMediaList().removeIf(s -> s.trim().equalsIgnoreCase(code));
                    mediaRental.getCustomers().get(i).getInterestedMediaList().removeIf(s -> s.trim().equalsIgnoreCase(code));
                }
                mediaRental.getMediaList().remove(index);
                updateAndSaveMedia();
                updateAndSaveRentedAndCart();
                mediaCodeText_delPage.clear();
                mediaTitleText_delPage.clear();
                numOfCopiesText_delPage.clear();
                artistNameText_del.clear();
                songListText_del.clear();
                gameWeightText_del.clear();
                ratingText_del.clear();


                showAlert("Success","The Media deleted Successfully",0);

            }else {
                showAlert("Error","Media Not Found ",1);
            }
        });


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ update Media Page ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


        updateMediaPane.setPadding(new Insets(50,50,50,50));
        updateMediaPage.getStylesheets().add("mainPageStyle.css");

        //page labels and textField
        Label mediaCode_updPage =new Label("Media ID");
        mediaCode_updPage.setAlignment(Pos.CENTER);
        TextField mediaCodeText_updPage = new TextField();
        mediaCodeText_updPage.setPromptText("Enter Media ID");
        mediaCodeText_updPage.setAlignment(Pos.CENTER);

        Label mediaTitle_updPage =new Label("Media Title");
        mediaTitle_updPage.setAlignment(Pos.CENTER);
        TextField mediaTitleText_updPage = new TextField();
        mediaTitleText_updPage.setAlignment(Pos.CENTER);
        mediaTitleText_updPage.setEditable(false);

        Label numOfCopies_updPage =new Label("Number Of Copies");
        numOfCopies_updPage.setAlignment(Pos.CENTER);

        TextField numOfCopiesText_updPage = new TextField();
        numOfCopiesText_updPage.setAlignment(Pos.CENTER);
        numOfCopiesText_updPage.setEditable(false);


        //---- Album ----
        Label artistName_upd = new Label("Artist Name");
        artistName_upd.setVisible(false);  //setting them to invisible and inactive until you chose media type
        artistName_upd.setAlignment(Pos.CENTER);

        TextField artistNameText_upd = new TextField();
        artistNameText_upd.setVisible(false);
        artistNameText_upd.setAlignment(Pos.CENTER);

        Label songList_upd = new Label("Song List");
        songList_upd.setVisible(false);
        songList_upd.setAlignment(Pos.CENTER);

        TextField songListText_upd = new TextField();
        songListText_upd.setVisible(false);
        songListText_upd.setAlignment(Pos.CENTER);

        //---- Movie ----
        Label rating_upd = new Label("Rating");
        rating_upd.setVisible(false);
        rating_upd.setAlignment(Pos.CENTER);

        ToggleGroup ratingGroup_upd = new ToggleGroup();

        RadioButton hr_upd = new RadioButton("HR");
        hr_upd.setAlignment(Pos.CENTER);
        hr_upd.setToggleGroup(ratingGroup_upd);
        hr_upd.setDisable(true);
        hr_upd.setVisible(false);



        RadioButton dr_upd = new RadioButton("DR");
        dr_upd.setAlignment(Pos.CENTER);
        dr_upd.setToggleGroup(ratingGroup_upd);
        dr_upd.setDisable(true);
        dr_upd.setVisible(false);



        RadioButton ac_upd = new RadioButton("AC");
        ac_upd.setAlignment(Pos.CENTER);
        ac_upd.setToggleGroup(ratingGroup_upd);
        ac_upd.setDisable(true);
        ac_upd.setVisible(false);

        HBox rating_co_upd = new HBox();
        rating_co_upd.setAlignment(Pos.CENTER);
        rating_co_upd.setSpacing(30);
        rating_co_upd.getChildren().addAll(hr_upd,dr_upd,ac_upd);

        //---- Game ----
        Label gameWeight_upd = new Label("Weight");
        gameWeight_upd.setAlignment(Pos.CENTER);
        gameWeight_upd.setVisible(false);

        TextField gameWeightText_upd = new TextField();
        gameWeightText_upd.setAlignment(Pos.CENTER);
        gameWeightText_upd.setVisible(false);





        GridPane updMedia_GP = new GridPane();

        updMedia_GP.add(mediaCode_updPage,0,0);
        updMedia_GP.add(mediaCodeText_updPage,1,0);

        updMedia_GP.add(mediaTitle_updPage,0,1);
        updMedia_GP.add(mediaTitleText_updPage,1,1);

        updMedia_GP.add(numOfCopies_updPage,0,2);
        updMedia_GP.add(numOfCopiesText_updPage,1,2);

        //---- Album ----
        updMedia_GP.add(artistName_upd,0,3);
        updMedia_GP.add(artistNameText_upd,1,3);

        updMedia_GP.add(songList_upd,0,4);
        updMedia_GP.add(songListText_upd,1,4);

        //---- Movie ----
        updMedia_GP.add(rating_upd,0,3);
        updMedia_GP.add(rating_co_upd,1,3);

        //---- Game ----
        updMedia_GP.add(gameWeight_upd,0,3);
        updMedia_GP.add(gameWeightText_upd,1,3);


        updateMediaPane.setCenter(updMedia_GP);

        updMedia_GP.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(updMedia_GP,Pos.CENTER);
        updMedia_GP.setVgap(15);
        updMedia_GP.setHgap(50);

        //Back button
        Button backBt_updMediaPage = new Button("Back");

        Image backImage_updMediaPage = new Image("back.png");
        ImageView backImageView_updMediaPage = new ImageView(backImage_updMediaPage);
        backImageView_updMediaPage.setFitWidth(30);
        backImageView_updMediaPage.setFitHeight(30);

        backBt_updMediaPage.setGraphic(backImageView_updMediaPage);
        backBt_updMediaPage.setGraphicTextGap(15);

        //Find button

        Button findBt_updMediaPage = new Button("Find");

        Image findImage_updMediaPage = new Image("search.png");
        ImageView findImageView_updMediaPage = new ImageView(findImage_updMediaPage);
        findBt_updMediaPage.getStylesheets().add("findButtonStyle.css");
        findImageView_updMediaPage.setFitWidth(30);
        findImageView_updMediaPage.setFitHeight(30);

        findBt_updMediaPage.setGraphic(findImageView_updMediaPage);
        findBt_updMediaPage.setGraphicTextGap(15);

        //Update button

        Button UpdBt_UpdMediaPage = new Button("Update");

        Image UpdImage_UpdMediaPage = new Image("update.png");
        ImageView UpdImageView_UpdMediaPage = new ImageView(UpdImage_UpdMediaPage);
        UpdBt_UpdMediaPage.getStylesheets().add("addButtonStyle.css");
        UpdImageView_UpdMediaPage.setFitWidth(30);
        UpdImageView_UpdMediaPage.setFitHeight(30);

        UpdBt_UpdMediaPage.setGraphic(UpdImageView_UpdMediaPage);
        UpdBt_UpdMediaPage.setGraphicTextGap(15);

        //Container for the three buttons
        HBox updMediaBtCon = new HBox();
        updMediaBtCon.setAlignment(Pos.CENTER);
        updMediaBtCon.setSpacing(100);
        updMediaBtCon.getChildren().addAll(backBt_updMediaPage,findBt_updMediaPage,UpdBt_UpdMediaPage);

        // adding buttons to the Scene (UPDATE page)
        updateMediaPane.setBottom(updMediaBtCon);
        BorderPane.setAlignment(updMediaBtCon,Pos.CENTER);

        //button action
        backBt_updMediaPage.setOnAction(e-> {
            stage.setScene(mediaPage);

            mediaCodeText_updPage.clear();         // resetting text fields when exit the page
            mediaTitleText_updPage.clear();
            mediaTitleText_updPage.setEditable(false);
            numOfCopiesText_updPage.clear();
            numOfCopiesText_updPage.setEditable(false);

            artistName_upd.setVisible(false);
            artistNameText_upd.clear();
            artistNameText_upd.setVisible(false);
            artistNameText_upd.setEditable(false);
            songList_upd.setVisible(false);
            songListText_upd.clear();
            songListText_upd.setVisible(false);
            songListText_upd.setEditable(false);

            rating_upd.setVisible(false);
            hr_upd.setSelected(false);
            hr_upd.setDisable(true);
            hr_upd.setVisible(false);
            ac_upd.setSelected(false);
            ac_upd.setDisable(true);
            ac_upd.setVisible(false);
            dr_upd.setSelected(false);
            dr_upd.setDisable(true);
            dr_upd.setVisible(false);

            gameWeight_upd.setVisible(false);
            gameWeightText_upd.clear();
            gameWeightText_upd.setVisible(false);
            gameWeightText_upd.setEditable(false);


        });

        findBt_updMediaPage.setOnAction(e->{
            String code = mediaCodeText_updPage.getText();
            int index = searchMediaByCode(code);

            if(index >=0){
                mediaCodeText_updPage.setEditable(true);
                mediaTitleText_updPage.setEditable(true);
                numOfCopiesText_updPage.setEditable(true);

                if(mediaRental.getMediaList().get(index) instanceof Game){  //test if it is movie or album or game

                    gameWeight_upd.setVisible(true);
                    gameWeightText_upd.setVisible(true);
                    gameWeightText_upd.setEditable(true);

                    rating_upd.setVisible(false);
                    hr_upd.setSelected(false);
                    hr_upd.setDisable(true);
                    ac_upd.setSelected(false);
                    ac_upd.setDisable(true);
                    dr_upd.setSelected(false);
                    dr_upd.setDisable(true);
                    artistName_upd.setVisible(false);
                    artistNameText_upd.clear();
                    artistNameText_upd.setVisible(false);
                    artistNameText_upd.setEditable(false);
                    songList_upd.setVisible(false);
                    songListText_upd.clear();
                    songListText_upd.setVisible(false);
                    songListText_upd.setEditable(false);

                    mediaTitleText_updPage.setText(mediaRental.getMediaList().get(index).getTitle());          //view results depending on its type
                    numOfCopiesText_updPage.setText(mediaRental.getMediaList().get(index).getNumOfCopies() + "");
                    gameWeightText_upd.setText(((Game) mediaRental.getMediaList().get(index)).getWeight() + "");


                }else if (mediaRental.getMediaList().get(index) instanceof Album){

                    artistName_upd.setVisible(true);
                    artistNameText_upd.setVisible(true);
                    artistNameText_upd.setEditable(true);
                    songList_upd.setVisible(true);
                    songListText_upd.setVisible(true);
                    songListText_upd.setEditable(true);


                    rating_upd.setVisible(false);
                    hr_upd.setSelected(false);
                    hr_upd.setDisable(true);
                    ac_upd.setSelected(false);
                    ac_upd.setDisable(true);
                    dr_upd.setSelected(false);
                    dr_upd.setDisable(true);
                    gameWeight_upd.setVisible(false);
                    gameWeightText_upd.setVisible(false);
                    gameWeightText_upd.clear();
                    gameWeightText_upd.setEditable(false);

                    mediaTitleText_updPage.setText(mediaRental.getMediaList().get(index).getTitle());
                    numOfCopiesText_updPage.setText(mediaRental.getMediaList().get(index).getNumOfCopies() + "");
                    artistNameText_upd.setText(((Album) mediaRental.getMediaList().get(index)).getArtist());
                    songListText_upd.setText(((Album) mediaRental.getMediaList().get(index)).getSongs().toString().replaceAll("[\\[\\]]", "").trim());

                }else if (mediaRental.getMediaList().get(index) instanceof Movie){

                    rating_upd.setVisible(true);
                    hr_upd.setDisable(false);
                    hr_upd.setVisible(true);
                    ac_upd.setDisable(false);
                    ac_upd.setVisible(true);
                    dr_upd.setDisable(false);
                    dr_upd.setVisible(true);

                    gameWeight_upd.setVisible(false);
                    gameWeightText_upd.setVisible(false);
                    gameWeightText_upd.clear();
                    gameWeightText_upd.setEditable(false);
                    artistName_upd.setVisible(false);
                    artistNameText_upd.clear();
                    artistNameText_upd.setVisible(false);
                    artistNameText_upd.setEditable(false);
                    songList_upd.setVisible(false);
                    songListText_upd.clear();
                    songListText_upd.setVisible(false);
                    songListText_upd.setEditable(false);

                    mediaTitleText_updPage.setText(mediaRental.getMediaList().get(index).getTitle());
                    numOfCopiesText_updPage.setText(mediaRental.getMediaList().get(index).getNumOfCopies() + "");
                    if (((Movie) mediaRental.getMediaList().get(index)).getRating().equalsIgnoreCase("hr")){
                        hr_upd.setSelected(true);
                    }else if (((Movie) mediaRental.getMediaList().get(index)).getRating().equalsIgnoreCase("dr")){
                        dr_upd.setSelected(true);
                    }else {
                        ac_upd.setSelected(true);
                    }

                }
            }else {
                showAlert("Error","Media Not Found ",1);
            }

            mediaCodeText_updPage.setOnKeyReleased(event->{      //clear text field when the id changed after getting the result
                mediaTitleText_updPage.clear();
                mediaTitleText_updPage.setEditable(false);
                numOfCopiesText_updPage.clear();
                numOfCopiesText_updPage.setEditable(false);

                artistName_upd.setVisible(false);
                artistNameText_upd.clear();
                artistNameText_upd.setVisible(false);
                artistNameText_upd.setEditable(false);
                songList_upd.setVisible(false);
                songListText_upd.clear();
                songListText_upd.setVisible(false);
                songListText_upd.setEditable(false);

                rating_upd.setVisible(false);
                hr_upd.setSelected(false);
                hr_upd.setDisable(true);
                hr_add.setVisible(false);
                ac_upd.setSelected(false);
                ac_upd.setDisable(true);
                ac_upd.setVisible(false);
                dr_upd.setSelected(false);
                dr_upd.setDisable(true);
                dr_upd.setVisible(false);

                gameWeight_upd.setVisible(false);
                gameWeightText_upd.clear();
                gameWeightText_upd.setVisible(false);
                gameWeightText_upd.setEditable(false);


            });


        });

        UpdBt_UpdMediaPage.setOnAction(e->{
            if(mediaCodeText_updPage.getText().trim().matches("[0-9]+") && !mediaTitleText_updPage.getText().trim().isEmpty() ) {
                String code = mediaCodeText_updPage.getText().trim();
                String mediaTitle = mediaTitleText_updPage.getText().trim();
                String numOfCopies = numOfCopiesText_updPage.getText().trim();
                int index = searchMediaByCode(code);

                if(index >= 0) {
                    try {
                        if (mediaRental.getMediaList().get(index) instanceof Game) {
                            String weight = gameWeightText_upd.getText().trim();

                            mediaRental.getMediaList().get(index).setTitle(mediaTitle);
                            mediaRental.getMediaList().get(index).setNumOfCopies(Integer.parseInt(numOfCopies));
                            ((Game) mediaRental.getMediaList().get(index)).setWeight(Double.parseDouble(weight));

                            gameWeightText_upd.setEditable(false);

                        } else if (mediaRental.getMediaList().get(index) instanceof Album) {
                            String artistName = artistNameText_upd.getText().trim();
                            String songList = songListText_upd.getText().trim();

                            mediaRental.getMediaList().get(index).setTitle(mediaTitle);
                            mediaRental.getMediaList().get(index).setNumOfCopies(Integer.parseInt(numOfCopies));
                            ((Album) mediaRental.getMediaList().get(index)).setArtist(artistName);
                            String[] arrOfSongs = songList.trim().split(",");
                            Arrays.parallelSetAll(arrOfSongs, (i) -> arrOfSongs[i].trim());    // trimming every single element in the array (lambda expression)
                            ((Album) mediaRental.getMediaList().get(index)).setSongs(new ArrayList<>(Arrays.asList(arrOfSongs)));

                            artistNameText_upd.setEditable(false);
                            songListText_upd.setEditable(false);


                        } else if (mediaRental.getMediaList().get(index) instanceof Movie) {
                            String rating;
                            if (hr_upd.isSelected()) {
                                rating = "hr";
                            } else if (ac_upd.isSelected()) {
                                rating = "ac";
                            } else {
                                rating = "dr";
                            }
                            mediaRental.getMediaList().get(index).setTitle(mediaTitle);
                            mediaRental.getMediaList().get(index).setNumOfCopies(Integer.parseInt(numOfCopies));
                            ((Movie) mediaRental.getMediaList().get(index)).setRating(rating);

                            hr_upd.setDisable(true);
                            ac_upd.setDisable(true);
                            dr_upd.setDisable(true);

                        }

                        mediaTitleText_updPage.setEditable(false);
                        numOfCopiesText_updPage.setEditable(false);
                        updateAndSaveMedia();
                        updateAndSaveRentedAndCart();
                        showAlert("Success", "The Media Updated Successfully", 0);
                    }catch (Exception exception){
                        showAlert("Error","Error,incorrect "+exception.getMessage(),1);
                    }
                }else {
                    showAlert("Error","Media doesnt Exist",1);
                }
            }
            else {
                showAlert("Error","Please enter correct data ",1);
            }
        });




    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Search Media Page ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        searchMediaPane.setPadding(new Insets(50,50,50,50));
        searchMediaPage.getStylesheets().add("mainPageStyle.css");

        //page labels and textField and toggle group
        Label mediaCode_serPage =new Label("Media Code");
        mediaCode_serPage.setAlignment(Pos.CENTER);
        TextField mediaCodeText_serPage = new TextField();
        mediaCodeText_serPage.setPromptText("Enter Media Code");
        mediaCodeText_serPage.setAlignment(Pos.CENTER);


        Label mediaTitle_serPage =new Label("Media Title");
        mediaTitle_serPage.setAlignment(Pos.CENTER);
        TextField mediaTitleText_serPage = new TextField();
        mediaTitleText_serPage.setAlignment(Pos.CENTER);
        mediaTitleText_serPage.setEditable(false);
        mediaTitleText_serPage.setEditable(false);

        Label numOfCopies_serPage =new Label("Number Of Copies");
        numOfCopies_serPage.setAlignment(Pos.CENTER);

        TextField numOfCopiesText_serPage = new TextField();
        numOfCopiesText_serPage.setAlignment(Pos.CENTER);
        numOfCopiesText_serPage.setEditable(false);
        numOfCopiesText_serPage.setEditable(false);


        //---- Album ----
        Label artistName_ser = new Label("Artist Name");
        artistName_ser.setVisible(false);  //setting them to invisible and inactive until you chose media type
        artistName_ser.setAlignment(Pos.CENTER);

        TextField artistNameText_ser = new TextField();
        artistNameText_ser.setVisible(false);
        artistNameText_ser.setAlignment(Pos.CENTER);
        artistNameText_ser.setEditable(false);
        Label songList_ser = new Label("Song List");
        songList_ser.setVisible(false);
        songList_ser.setAlignment(Pos.CENTER);

        TextField songListText_ser = new TextField();
        songListText_ser.setVisible(false);
        songListText_ser.setAlignment(Pos.CENTER);
        songListText_ser.setEditable(false);
        //---- Movie ----
        Label rating_ser = new Label("Rating");
        rating_ser.setVisible(false);
        rating_ser.setAlignment(Pos.CENTER);

        TextField ratingText_ser = new TextField();
        ratingText_ser.setVisible(false);
        ratingText_ser.setAlignment(Pos.CENTER);
        ratingText_ser.setEditable(false);
        //---- Game ----
        Label gameWeight_ser = new Label("Weight");
        gameWeight_ser.setAlignment(Pos.CENTER);
        gameWeight_ser.setVisible(false);

        TextField gameWeightText_ser = new TextField();
        gameWeightText_ser.setAlignment(Pos.CENTER);
        gameWeightText_ser.setVisible(false);
        gameWeightText_ser.setEditable(false);




        GridPane serMedia_GP = new GridPane();

        serMedia_GP.add(mediaCode_serPage,0,0);
        serMedia_GP.add(mediaCodeText_serPage,1,0);

        serMedia_GP.add(mediaTitle_serPage,0,1);
        serMedia_GP.add(mediaTitleText_serPage,1,1);

        serMedia_GP.add(numOfCopies_serPage,0,2);
        serMedia_GP.add(numOfCopiesText_serPage,1,2);

        //---- Album ----
        serMedia_GP.add(artistName_ser,0,3);
        serMedia_GP.add(artistNameText_ser,1,3);

        serMedia_GP.add(songList_ser,0,4);
        serMedia_GP.add(songListText_ser,1,4);

        //---- Movie ----
        serMedia_GP.add(rating_ser,0,3);
        serMedia_GP.add(ratingText_ser,1,3);

        //---- Game ----
        serMedia_GP.add(gameWeight_ser,0,3);
        serMedia_GP.add(gameWeightText_ser,1,3);


        searchMediaPane.setCenter(serMedia_GP);

        serMedia_GP.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(serMedia_GP,Pos.CENTER);
        serMedia_GP.setVgap(15);
        serMedia_GP.setHgap(50);

        //Back button
        Button backBt_serMediaPage = new Button("Back");

        Image backImage_serMediaPage = new Image("back.png");
        ImageView backImageView_serMediaPage = new ImageView(backImage_serMediaPage);
        backImageView_serMediaPage.setFitWidth(30);
        backImageView_serMediaPage.setFitHeight(30);

        backBt_serMediaPage.setGraphic(backImageView_serMediaPage);
        backBt_serMediaPage.setGraphicTextGap(15);

        //Find button

        Button findBt_serMediaPage = new Button("Find");

        Image findImage_serMediaPage = new Image("search.png");
        ImageView findImageView_serMediaPage = new ImageView(findImage_serMediaPage);
        findBt_serMediaPage.getStylesheets().add("findButtonStyle.css");
        findImageView_serMediaPage.setFitWidth(30);
        findImageView_serMediaPage.setFitHeight(30);

        findBt_serMediaPage.setGraphic(findImageView_serMediaPage);
        findBt_serMediaPage.setGraphicTextGap(15);

        HBox serMediaBtCon = new HBox();
        serMediaBtCon.setAlignment(Pos.CENTER);
        serMediaBtCon.setSpacing(100);
        serMediaBtCon.getChildren().addAll(backBt_serMediaPage,findBt_serMediaPage);

        searchMediaPane.setBottom(serMediaBtCon);
        BorderPane.setAlignment(serMediaBtCon,Pos.CENTER);

        //buttons action
        backBt_serMediaPage.setOnAction(e-> {
            stage.setScene(mediaPage);

            mediaCodeText_serPage.clear();
            mediaTitleText_serPage.clear();
            numOfCopiesText_serPage.clear();

            artistName_ser.setVisible(false);
            artistNameText_ser.clear();
            artistNameText_ser.setVisible(false);
            songList_ser.setVisible(false);
            songListText_ser.clear();
            songListText_ser.setVisible(false);

            gameWeight_ser.setVisible(false);
            gameWeightText_ser.clear();
            gameWeightText_ser.setVisible(false);

            rating_ser.setVisible(false);
            ratingText_ser.clear();
            ratingText_ser.setVisible(false);

        });

        findBt_serMediaPage.setOnAction(e->{
            String code = mediaCodeText_serPage.getText().trim();
            int index = searchMediaByCode(code);

            if( index >=0 ){

                if(mediaRental.getMediaList().get(index) instanceof Game){  //test if it is movie or album or game

                    gameWeight_ser.setVisible(true);
                    gameWeightText_ser.setVisible(true);

                    rating_ser.setVisible(false);
                    ratingText_ser.clear();
                    ratingText_ser.setVisible(false);
                    artistName_ser.setVisible(false);
                    artistNameText_ser.clear();
                    artistNameText_ser.setVisible(false);
                    songList_ser.setVisible(false);
                    songListText_ser.clear();
                    songListText_ser.setVisible(false);

                    mediaTitleText_serPage.setText(mediaRental.getMediaList().get(index).getTitle());          //view results depending on its type
                    numOfCopiesText_serPage.setText(mediaRental.getMediaList().get(index).getNumOfCopies() + "");
                    gameWeightText_ser.setText(((Game) mediaRental.getMediaList().get(index)).getWeight() + "");


                }else if (mediaRental.getMediaList().get(index) instanceof Album){

                    artistName_ser.setVisible(true);
                    artistNameText_ser.setVisible(true);
                    songList_ser.setVisible(true);
                    songListText_ser.setVisible(true);

                    gameWeight_ser.setVisible(false);
                    gameWeightText_ser.clear();
                    gameWeightText_ser.setVisible(false);
                    rating_ser.setVisible(false);
                    ratingText_ser.clear();
                    ratingText_ser.setVisible(false);

                    mediaTitleText_serPage.setText(mediaRental.getMediaList().get(index).getTitle());
                    numOfCopiesText_serPage.setText(mediaRental.getMediaList().get(index).getNumOfCopies() + "");
                    artistNameText_ser.setText(((Album) mediaRental.getMediaList().get(index)).getArtist());
                    songListText_ser.setText(((Album) mediaRental.getMediaList().get(index)).getSongs().toString().trim());

                }else if (mediaRental.getMediaList().get(index) instanceof Movie){

                    rating_ser.setVisible(true);
                    ratingText_ser.setVisible(true);

                    gameWeight_ser.setVisible(false);
                    gameWeightText_ser.clear();
                    gameWeightText_ser.setVisible(false);
                    artistName_ser.setVisible(false);
                    artistNameText_ser.clear();
                    artistNameText_ser.setVisible(false);
                    songList_ser.setVisible(false);
                    songListText_ser.clear();
                    songListText_ser.setVisible(false);

                    mediaTitleText_serPage.setText(mediaRental.getMediaList().get(index).getTitle());
                    numOfCopiesText_serPage.setText(mediaRental.getMediaList().get(index).getNumOfCopies() + "");
                    ratingText_del.setText(((Movie) mediaRental.getMediaList().get(index)).getRating());

                }

            }else if (index == -1){
                showAlert("Error","Media Not Found ",1);
            }

            mediaCodeText_delPage.setOnKeyTyped(event->{      //clear text field when the id changed after getting the result
                mediaTitleText_serPage.clear();
                numOfCopiesText_serPage.clear();
                gameWeightText_ser.clear();
                artistNameText_ser.clear();
                songListText_ser.clear();
                ratingText_ser.clear();

                artistName_ser.setVisible(false);
                artistNameText_ser.setVisible(false);
                songList_ser.setVisible(false);
                songListText_ser.setVisible(false);
                gameWeight_ser.setVisible(false);
                gameWeightText_ser.setVisible(false);
                rating_ser.setVisible(false);
                ratingText_ser.setVisible(false);

            });


        });


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ print all media info ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        printMediaPane.setPadding(new Insets(50,50,50,50));
        printMediaPage.getStylesheets().add("mainPageStyle.css");

        Label data_mediaPage = new Label("All Media Info");
        data_mediaPage.setAlignment(Pos.CENTER);


        TextArea out1_mediaPage = new TextArea();
        out1_mediaPage.setEditable(false);
        out1_mediaPage.setMaxWidth(600);
        out1_mediaPage.setPrefHeight(400);
        out1_mediaPage.setStyle("-fx-font-size: 16;-fx-font-family: 'Arial Rounded MT Bold'");


        VBox printMedElements = new VBox();
        printMedElements.getChildren().addAll(data_mediaPage,out1_mediaPage);
        printMedElements.setSpacing(30);
        printMedElements.setAlignment(Pos.CENTER);

        printMediaPane.setCenter(printMedElements);
        BorderPane.setAlignment(printMedElements,Pos.CENTER);


        //Back button
        Button backBt_printMediaPage = new Button("Back");

        Image backImage_printMediaPage = new Image("back.png");
        ImageView backImageView_printMediaPage = new ImageView(backImage_printMediaPage);
        backImageView_printMediaPage.setFitWidth(30);
        backImageView_printMediaPage.setFitHeight(30);

        backBt_printMediaPage.setGraphic(backImageView_printMediaPage);
        backBt_printMediaPage.setGraphicTextGap(15);

        //Print button

        Button printBt_printMediaPage = new Button("Print");

        Image printImage_printMediaPage = new Image("print.png");
        ImageView printImageView_printMediaPage= new ImageView(printImage_printMediaPage);
        printBt_printMediaPage.getStylesheets().add("findButtonStyle.css");
        printImageView_printMediaPage.setFitWidth(30);
        printImageView_printMediaPage.setFitHeight(30);

        printBt_printMediaPage.setGraphic(printImageView_printMediaPage);
        printBt_printMediaPage.setGraphicTextGap(15);

        HBox btCont_printMediaPage = new HBox();
        btCont_printMediaPage.getChildren().addAll(backBt_printMediaPage,printBt_printMediaPage);
        btCont_printMediaPage.setSpacing(15);
        btCont_printMediaPage.setAlignment(Pos.CENTER);

        printMediaPane.setBottom(btCont_printMediaPage);


        backBt_printMediaPage.setOnAction(e-> {
            stage.setScene(mediaPage);
            out1_mediaPage.clear();
        });
        printBt_printMediaPage.setOnAction(e-> {
            if (mediaRental.getMediaList().size()>0){
                out1_mediaPage.setText(mediaRental.getAllMediaInfo());
            }else {
                showAlert("Error","There is No Media Added",1);
            }

        });

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Rent Page ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        rentPane.setPadding(new Insets(50,50,50,50));
        rentPage.getStylesheets().add("mainPageStyle.css");

        //buttons in customer page
        Button rentB1 = new Button("Rent To Customer");
        Button rentB2 = new Button("Print Requested");
        Button rentB3 = new Button("Print Rented");
        Button rentB4 = new Button("Return Rented");
        Button rentB5 = new Button("Change Limit for limited user");

        VBox rentMenu = new VBox();
        rentMenu.setSpacing(25);
        rentMenu.setPadding(new Insets(30,30,30,30));
        rentMenu.getChildren().addAll(rentB1,rentB2,rentB3,rentB4,rentB5);
        rentMenu.setAlignment(Pos.CENTER);
        rentPane.setCenter(rentMenu);
        BorderPane.setAlignment(rentMenu,Pos.CENTER);

        //BACK buttons
        Button backBt1_rentPage = new Button("Return to main page");

        Image backImage1_rentPage = new Image("back.png");
        ImageView backImageView1_rentPage = new ImageView(backImage1_rentPage);
        backImageView1_rentPage.setFitWidth(30);
        backImageView1_rentPage.setFitHeight(30);

        backBt1_rentPage.setGraphic(backImageView1_rentPage);
        backBt1_rentPage.setGraphicTextGap(15);

        rentPane.setBottom(backBt1_rentPage);
        BorderPane.setAlignment(backBt1_rentPage,Pos.CENTER);

        backBt1_rentPage.setOnAction(e -> stage.setScene(mainPage) );  //Actions for the  buttons
        rentB1.setOnAction(e-> stage.setScene(rentToCusPage) );
        rentB2.setOnAction(e-> stage.setScene(printRequestedPage) );
        rentB3.setOnAction(e-> stage.setScene(printRentedPage));
        rentB4.setOnAction(e->stage.setScene(returnRentedPage));
        rentB5.setOnAction(e->stage.setScene(setLimitPage));

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Rent to Cus Page ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        rentToCusPane.setPadding(new Insets(50,50,50,50));
        rentToCusPage.getStylesheets().add("mainPageStyle.css");

        Label cusIDLabel =new Label("Customer ID");
        cusIDLabel.setAlignment(Pos.CENTER);

        TextField cusIdIn = new TextField();
        cusIdIn.setPromptText("Enter Customer ID");
        cusIdIn.setAlignment(Pos.CENTER);

        TextArea infoCus = new TextArea();
        infoCus.setEditable(false);
        infoCus.setPromptText("Enter ID and press enter to view info");
        infoCus.setStyle("-fx-font-size: 16;-fx-font-family: 'Arial Rounded MT Bold'");

        Label mediaCodeLabel =new Label("Media Code");
        mediaCodeLabel.setAlignment(Pos.CENTER);

        TextField mediaCode = new TextField();
        mediaCode.setPromptText("Enter Media code");
        mediaCode.setAlignment(Pos.CENTER);

        TextArea infoMedia = new TextArea();
        infoMedia.setEditable(false);
        infoMedia.setPromptText("Enter Code and press enter to view info");
        infoMedia.setStyle("-fx-font-size: 16;-fx-font-family: 'Arial Rounded MT Bold'");

        Label rentedDate = new Label("Rented Date");
        rentedDate.setAlignment(Pos.CENTER);
        TextField rentDateField = new TextField();
        rentDateField.setAlignment(Pos.CENTER);
        rentDateField.setEditable(false);
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        rentDateField.setText(format.format(date));

        GridPane rentGP = new GridPane();

        rentGP.add(cusIDLabel,0,0);
        rentGP.add(cusIdIn,1,0);
        rentGP.add(infoCus,1,1);

        rentGP.add(mediaCodeLabel,0,2);
        rentGP.add(mediaCode,1,2);
        rentGP.add(infoMedia,1,3);

        rentGP.add(rentedDate,0,4);
        rentGP.add(rentDateField,1,4);

        rentToCusPane.setCenter(rentGP);


        rentGP.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(rentGP,Pos.CENTER);
        rentGP.setVgap(15);
        rentGP.setHgap(50);


        //rent page buttons
        Button addToCartButton = new Button("Add To Cart");
        Button processCartButton = new Button("Process Cart");
        Button backBt3 = new Button("Back");
        Button removeFromCart = new Button("Remove From Cart");

        //style for the buttons
        addToCartButton.getStylesheets().add("addToCartBT.css");
        processCartButton.getStylesheets().add("addButtonStyle.css");
        removeFromCart.getStylesheets().add("deleteButtonStyle.css");

        Image backImage3 = new Image("back.png");           //image for back button
        ImageView backImageView3 = new ImageView(backImage3);
        backImageView3.setFitWidth(30);
        backImageView3.setFitHeight(30);
        backBt3.setGraphic(backImageView3);
        backBt3.setGraphicTextGap(15);

        Image addToC = new Image("addToCart.png");              //image for add to cart button
        ImageView addToCView = new ImageView(addToC);
        addToCView.setFitHeight(30);
        addToCView.setFitWidth(30);
        addToCartButton.setGraphic(addToCView);
        addToCartButton.setGraphicTextGap(15);

        Image processImage = new Image("process.png");              //image for process button
        ImageView processImageView = new ImageView(processImage);
        processImageView.setFitHeight(30);
        processImageView.setFitWidth(30);
        processCartButton.setGraphic(processImageView);
        processCartButton.setGraphicTextGap(15);

        Image removeFCImage = new Image("remove-from-cart.png");              //image for process button
        ImageView removeFCImageView = new ImageView(removeFCImage);
        removeFCImageView.setFitHeight(30);
        removeFCImageView.setFitWidth(30);
        removeFromCart.setGraphic(removeFCImageView);
        removeFromCart.setGraphicTextGap(15);

        HBox rentButtonGroup = new HBox();
        rentButtonGroup.getChildren().addAll(backBt3,removeFromCart,addToCartButton,processCartButton);
        rentButtonGroup.setSpacing(15);
        rentToCusPane.setBottom(rentButtonGroup);
        rentButtonGroup.setAlignment(Pos.CENTER);

        backBt3.setOnAction(e -> {        //Actions for the buttons
            stage.setScene(rentPage);
            cusIdIn.clear();
            infoCus.clear();
            mediaCode.clear();
            infoMedia.clear();
            rentDateField.clear();
        } );

        addToCartButton.setOnAction(e->{
            String id = cusIdIn.getText().trim();
            String code = mediaCode.getText().trim();
            int index = searchCusById(id);
            if (!id.isEmpty() && id.matches("[0-9]+") ){
                if (index >=0){
                    if(mediaRental.addToCart(id,code)){
                        updateAndSaveMedia();
                        updateAndSaveRentedAndCart();
                        showAlert("Success","The media added Successfully",0);
                    }else {
                        showAlert("Error","The Media Already In The Cart",1);
                    }
                }else {
                    showAlert("Error","Customer doesnt Exist ",1);
                }
            }else {
                showAlert("Error","Please Enter a Valid Customer Id",1);
            }
        });

        processCartButton.setOnAction(e->{

            String id = cusIdIn.getText().trim();
            int index = searchCusById(id);

            if (!id.isEmpty() && id.matches("[0-9]+") ){
                if (index >=0){
                    if (mediaRental.getCustomers().get(index).getInterestedMediaList().size()>0){
                        File logs = new File("logs.txt");
                        try{
                            FileWriter fr = new FileWriter(logs, true);
                            fr.write(mediaRental.processRequests(id));
                            fr.close();
                            updateAndSaveMedia();
                            updateAndSaveRentedAndCart();
                            showAlert("Success","The cart processed Successfully\n(logs added to logs.txt file)",0);

                        } catch (Exception ex) {
                            showAlert("Error",ex.getMessage(),1);
                        }
                    }else {
                        showAlert("Error","Customer Cart Is Empty",1);
                    }
                }else {
                    showAlert("Error","Customer doesnt Exist ",1);
                }

            }else {
                showAlert("Error","Please Enter a Valid Customer Id",1);
            }
        });

        removeFromCart.setOnAction(e->{
            String id = cusIdIn.getText().trim();
            String code = mediaCode.getText().trim();
            int index = searchCusById(id);

            if (!id.isEmpty() && id.matches("[0-9]+") ){
                if (index >=0){
                    if (mediaRental.getCustomers().get(index).getInterestedMediaList().size()>0){
                        if(mediaRental.removeFromCart(id,code)){
                            updateAndSaveMedia();
                            updateAndSaveRentedAndCart();
                            showAlert("Success","The media removed Successfully",0);
                        }else {
                            showAlert("Error","An Error has Occurred",1);
                        }
                    }else {
                        showAlert("Error","Customer Cart Is Empty",1);
                    }
                }else {
                    showAlert("Error","Customer doesnt Exist ",1);
                }
            }else {
                showAlert("Error","Please Enter a Valid Customer Id",1);
            }
        });

        cusIdIn.setOnKeyTyped(e->{
            String id = cusIdIn.getText().trim();
            int index = searchCusById(id);
            if (index>=0){
                infoCus.setText(mediaRental.getCustomers().get(index).toString());
            }else {
                infoCus.clear();
            }

        });

        mediaCode.setOnKeyTyped(e->{
            String code = mediaCode.getText().trim();
            int index = searchMediaByCode(code);
            if (index>=0){
                infoMedia.setText(mediaRental.getMediaList().get(index).toString());
            }else {
                infoMedia.clear();
            }


        });



    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ printRequestedPage ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        printRequestedPane.setPadding(new Insets(50,50,50,50));
        printRequestedPage.getStylesheets().add("mainPageStyle.css");

        Label cusId_rentPage = new Label("Customer ID");
        cusId_rentPage.setAlignment(Pos.CENTER);
        TextField cusIdText_rentPage = new TextField();
        cusIdText_rentPage.setPromptText("Enter Customer Id");
        cusIdText_rentPage.setAlignment(Pos.CENTER);

        TextArea out1_rentPage = new TextArea();
        out1_rentPage.setEditable(false);
        out1_rentPage.setMaxWidth(600);
        out1_rentPage.setPrefHeight(400);
        out1_rentPage.setStyle("-fx-font-size: 16;-fx-font-family: 'Arial Rounded MT Bold'");

        HBox printReqInputs = new HBox();
        printReqInputs.getChildren().addAll(cusId_rentPage,cusIdText_rentPage);
        printReqInputs.setSpacing(15);
        printReqInputs.setAlignment(Pos.CENTER);

        VBox printReqElements = new VBox();
        printReqElements.getChildren().addAll(printReqInputs,out1_rentPage);
        printReqElements.setSpacing(30);
        printReqElements.setAlignment(Pos.CENTER);

        printRequestedPane.setCenter(printReqElements);
        BorderPane.setAlignment(printReqElements,Pos.CENTER);

        //Back button
        Button backBt_printReqPage = new Button("Back");

        Image backImage_printReqPage = new Image("back.png");
        ImageView backImageView_printReqPage = new ImageView(backImage_printReqPage);
        backImageView_printReqPage.setFitWidth(30);
        backImageView_printReqPage.setFitHeight(30);

        backBt_printReqPage.setGraphic(backImageView_printReqPage);
        backBt_printReqPage.setGraphicTextGap(15);

        //Print button

        Button printBt_printReqPage = new Button("Print");

        Image printImage_printReqPage = new Image("Print.png");
        ImageView printImageView_printReqPage = new ImageView(printImage_printReqPage);
        printBt_printReqPage.getStylesheets().add("findButtonStyle.css");
        printImageView_printReqPage.setFitWidth(30);
        printImageView_printReqPage.setFitHeight(30);

        printBt_printReqPage.setGraphic(printImageView_printReqPage);
        printBt_printReqPage.setGraphicTextGap(15);

        HBox btCont_printReqPage = new HBox();
        btCont_printReqPage.getChildren().addAll(backBt_printReqPage,printBt_printReqPage);
        btCont_printReqPage.setSpacing(15);
        btCont_printReqPage.setAlignment(Pos.CENTER);

        printRequestedPane.setBottom(btCont_printReqPage);

        // actions for the buttons
        backBt_printReqPage.setOnAction(e-> {
            stage.setScene(rentPage);
            cusIdText_rentPage.clear();
            out1_rentPage.clear();
        });
        printBt_printReqPage.setOnAction(e->{
            String id = cusIdText_rentPage.getText().trim();
            int indexOfCus = searchCusById(id);
            if (indexOfCus>=0){
                if (mediaRental.getCustomers().get(indexOfCus).getInterestedMediaList().size() == 0){
                    out1_rentPage.setText("- Empty");
                }else {
                    StringBuilder str = new StringBuilder();
                    for (int i = 0; i < mediaRental.getCustomers().get(indexOfCus).getInterestedMediaList().size(); i++) {
                        String temp = mediaRental.getCustomers().get(indexOfCus).getInterestedMediaList().get(i).trim();
                        int index = searchMediaByCode(temp);
                        if (index>=0){
                            str.append(mediaRental.getMediaList().get(index).toString()).append("\n\n");
                        }
                    }
                    out1_rentPage.setText(str.toString());
                }
            }else {
                showAlert("Error","Customer doesnt Exist ",1);
            }
        });
        cusIdText_rentPage.setOnKeyTyped(e->out1_rentPage.clear());


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ printRentedPage ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        printRentedPane.setPadding(new Insets(50,50,50,50));
        printRentedPage.getStylesheets().add("mainPageStyle.css");

        Label cusId2_rentPage = new Label("Customer ID");
        cusId2_rentPage.setAlignment(Pos.CENTER);
        TextField cusIdText2_rentPage = new TextField();
        cusIdText2_rentPage.setPromptText("Enter Customer Id");
        cusIdText2_rentPage.setAlignment(Pos.CENTER);

        TextArea out2_rentPage = new TextArea();
        out2_rentPage.setEditable(false);
        out2_rentPage.setMaxWidth(600);
        out2_rentPage.setPrefHeight(400);
        out2_rentPage.setStyle("-fx-font-size: 16;-fx-font-family: 'Arial Rounded MT Bold'");

        HBox printRentInputs = new HBox();
        printRentInputs.getChildren().addAll(cusId2_rentPage,cusIdText2_rentPage);
        printRentInputs.setSpacing(15);
        printRentInputs.setAlignment(Pos.CENTER);

        VBox printRentElements = new VBox();
        printRentElements.getChildren().addAll(printRentInputs,out2_rentPage);
        printRentElements.setSpacing(30);
        printRentElements.setAlignment(Pos.CENTER);

        printRentedPane.setCenter(printRentElements);
        BorderPane.setAlignment(printRentElements,Pos.CENTER);

        //Back button
        Button backBt_printRentPage = new Button("Back");

        Image backImage_printRentPage = new Image("back.png");
        ImageView backImageView_printRentPage = new ImageView(backImage_printRentPage);
        backImageView_printRentPage.setFitWidth(30);
        backImageView_printRentPage.setFitHeight(30);

        backBt_printRentPage.setGraphic(backImageView_printRentPage);
        backBt_printRentPage.setGraphicTextGap(15);

        //Print button

        Button printBt_printRentPage = new Button("Print");

        Image printImage_printRentPage = new Image("print.png");
        ImageView printImageView_printRentPage = new ImageView(printImage_printRentPage);
        printBt_printRentPage.getStylesheets().add("findButtonStyle.css");
        printImageView_printRentPage.setFitWidth(30);
        printImageView_printRentPage.setFitHeight(30);

        printBt_printRentPage.setGraphic(printImageView_printRentPage);
        printBt_printRentPage.setGraphicTextGap(15);

        HBox btCont_printRentPage = new HBox();
        btCont_printRentPage.getChildren().addAll(backBt_printRentPage,printBt_printRentPage);
        btCont_printRentPage.setSpacing(15);
        btCont_printRentPage.setAlignment(Pos.CENTER);

        printRentedPane.setBottom(btCont_printRentPage);

        backBt_printRentPage.setOnAction(e-> {
            stage.setScene(rentPage);
            cusIdText2_rentPage.clear();
            out2_rentPage.clear();
        });
        printBt_printRentPage.setOnAction(e->{
            String id = cusIdText2_rentPage.getText().trim();
            int indexOfCus = searchCusById(id);
            if (indexOfCus>=0){
                if (mediaRental.getCustomers().get(indexOfCus).getRentedMediaList().size() == 0){
                    out2_rentPage.setText("- Empty");
                }else {
                    StringBuilder str = new StringBuilder();
                    for (int i = 0; i < mediaRental.getCustomers().get(indexOfCus).getRentedMediaList().size(); i++) {
                        String temp = mediaRental.getCustomers().get(indexOfCus).getRentedMediaList().get(i).trim();
                        int index = searchMediaByCode(temp);
                        if (index>=0){
                            str.append(mediaRental.getMediaList().get(index).toString()).append("\n\n");
                        }
                    }
                    out2_rentPage.setText(str.toString());
                }
            }else {
                showAlert("Error","Customer doesnt Exist ",1);
            }
        });

        cusIdText2_rentPage.setOnKeyTyped(e->out2_rentPage.clear());


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ returnRentedPage ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        returnRentedPane.setPadding(new Insets(50,200,50,200));
        returnRentedPage.getStylesheets().add("mainPageStyle.css");


        Label cusIDLabel_returnRented =new Label("Customer ID");
        cusIDLabel_returnRented.setAlignment(Pos.CENTER);

        TextField cusIdIn_returnRented = new TextField();
        cusIdIn_returnRented.setPromptText("Enter Customer ID");
        cusIdIn_returnRented.setAlignment(Pos.CENTER);

        Button find_info_Cus_returnRented = new Button();
        Image find_image_returnRented = new Image("search.png");
        ImageView find_imageView_returnRented = new ImageView(find_image_returnRented);
        find_info_Cus_returnRented.getStylesheets().add("findButtonStyle.css");
        find_imageView_returnRented.setFitWidth(30);
        find_imageView_returnRented.setFitHeight(30);
        find_info_Cus_returnRented.setGraphic(find_imageView_returnRented);
        find_info_Cus_returnRented.setGraphicTextGap(15);
        find_info_Cus_returnRented.setMaxHeight(50);
        find_info_Cus_returnRented.setMaxWidth(50);


        TextArea infoCus_returnRented = new TextArea();  //
        infoCus_returnRented.setEditable(false);
        infoCus_returnRented.setMaxSize(800,100);
        infoCus_returnRented.setStyle("-fx-font-size: 16;-fx-font-family: 'Arial Rounded MT Bold'");

        HBox cus_info_returnRented = new HBox();
        cus_info_returnRented.getChildren().addAll(cusIDLabel_returnRented,cusIdIn_returnRented,find_info_Cus_returnRented);
        cus_info_returnRented.setSpacing(15);
        cus_info_returnRented.setAlignment(Pos.CENTER);


        Label mediaCodeLabel_returnRented =new Label("Media Code");
        mediaCodeLabel_returnRented.setAlignment(Pos.CENTER);

        TextField mediaCode_returnRented = new TextField();
        mediaCode_returnRented.setPromptText("Enter Media code");
        mediaCode_returnRented.setAlignment(Pos.CENTER);

        Button find_info_Media_returnRented = new Button();
        Image find_image_Media_returnRented = new Image("search.png");
        ImageView find_imageView_Media_returnRented = new ImageView(find_image_Media_returnRented);
        find_info_Media_returnRented.getStylesheets().add("findButtonStyle.css");
        find_imageView_Media_returnRented.setFitWidth(30);
        find_imageView_Media_returnRented.setFitHeight(30);
        find_info_Media_returnRented.setGraphic(find_imageView_Media_returnRented);
        find_info_Media_returnRented.setGraphicTextGap(15);
        find_info_Media_returnRented.setMaxHeight(50);
        find_info_Media_returnRented.setMaxWidth(50);

        TextArea infoMedia_returnRented = new TextArea();  //
        infoMedia_returnRented.setEditable(false);
        infoMedia_returnRented.setMaxSize(800,100);
        infoMedia_returnRented.setStyle("-fx-font-size: 16;-fx-font-family: 'Arial Rounded MT Bold'");

        HBox media_info_returnRented = new HBox();
        media_info_returnRented.getChildren().addAll(mediaCodeLabel_returnRented,mediaCode_returnRented,find_info_Media_returnRented);
        media_info_returnRented.setSpacing(15);
        media_info_returnRented.setAlignment(Pos.CENTER);

        VBox return_rented_elements_cont = new VBox();
        return_rented_elements_cont.getChildren().addAll(cus_info_returnRented,infoCus_returnRented,media_info_returnRented,infoMedia_returnRented);
        return_rented_elements_cont.setSpacing(20);
        return_rented_elements_cont.setAlignment(Pos.CENTER);

        returnRentedPane.setCenter(return_rented_elements_cont);
        BorderPane.setAlignment(return_rented_elements_cont,Pos.CENTER);

        //Back button
        Button backBt_returnRented = new Button("Back");

        Image backImage_returnRented = new Image("back.png");
        ImageView backImageView_returnRented = new ImageView(backImage_returnRented);
        backImageView_returnRented.setFitWidth(30);
        backImageView_returnRented.setFitHeight(30);

        backBt_returnRented.setGraphic(backImageView_returnRented);
        backBt_returnRented.setGraphicTextGap(15);

        //return button

        Button returnBt_returnRented = new Button("Return");

        Image printImage_returnRented = new Image("return.png");
        ImageView printImageView_returnRented = new ImageView(printImage_returnRented);
        returnBt_returnRented.getStylesheets().add("deleteButtonStyle.css");
        printImageView_returnRented.setFitWidth(30);
        printImageView_returnRented.setFitHeight(30);

        returnBt_returnRented.setGraphic(printImageView_returnRented);
        returnBt_returnRented.setGraphicTextGap(15);

        HBox btCont_returnRented = new HBox();
        btCont_returnRented.getChildren().addAll(backBt_returnRented,returnBt_returnRented);
        btCont_returnRented.setSpacing(15);
        btCont_returnRented.setAlignment(Pos.CENTER);

        returnRentedPane.setBottom(btCont_returnRented);

        backBt_returnRented.setOnAction(e-> {
            stage.setScene(rentPage);
            cusIdIn_returnRented.clear();
            infoCus_returnRented.clear();
            mediaCode_returnRented.clear();
            infoMedia_returnRented.clear();
        });

        find_info_Cus_returnRented.setOnAction(e->{
            String id = cusIdIn_returnRented.getText().trim();
            int index = searchCusById(id);
            if (index>=0){
                infoCus_returnRented.setText(mediaRental.getCustomers().get(index).toString());
            }else {
                infoCus_returnRented.clear();
            }
        });
        cusIdIn_returnRented.setOnKeyTyped(e-> infoCus_returnRented.clear());


        find_info_Media_returnRented.setOnAction(e->{
            String code = mediaCode_returnRented.getText().trim();
            int index = searchMediaByCode(code);
            if (index>=0){
                infoMedia_returnRented.setText(mediaRental.getMediaList().get(index).toString());
            }else {
                infoMedia_returnRented.clear();
            }
        });
        mediaCode_returnRented.setOnKeyTyped(e-> infoMedia_returnRented.clear());

        returnBt_returnRented.setOnAction(e->{
            String id = cusIdIn_returnRented.getText().trim();
            String code = mediaCode_returnRented.getText().trim();
            try {
                if(mediaRental.returnMedia(id, code)){
                    showAlert("Success","The Media Returned Successfully",0);
                }else {
                    showAlert("Error","An Error Occurred",1);
                }
            }catch (Exception exception){
                showAlert("Error",exception.getMessage(),1);
            }
        });


        cusIdIn_returnRented.setOnKeyTyped(e->infoCus_returnRented.clear());
        mediaCode_returnRented.setOnKeyTyped(e->infoMedia_returnRented.clear());





    //------------------------------ set limit page --------------------------------------------

        setLimitPane.setPadding(new Insets(50,50,50,50));
        setLimitPage.getStylesheets().add("mainPageStyle.css");

        Label limit_txt = new Label("Limit");
        limit_txt.setAlignment(Pos.CENTER);

        TextField limit_txtIn = new TextField();
        limit_txtIn.setAlignment(Pos.CENTER);
        limit_txtIn.setEditable(false);

        Button editBtLimit = new Button("Edit");

        Button setLimit = new Button("Set");
        setLimit.setVisible(false);
        setLimit.setDisable(true);

        HBox limit_Ct = new HBox();
        limit_Ct.getChildren().addAll(limit_txt,limit_txtIn);
        limit_Ct.setSpacing(15);
        limit_Ct.setAlignment(Pos.CENTER);
        limit_Ct.setMaxWidth(400);

        StackPane limit_Bt_Ct  = new StackPane();
        limit_Bt_Ct.getChildren().addAll(editBtLimit,setLimit);
        limit_Bt_Ct.setAlignment(Pos.CENTER);
        limit_Bt_Ct.setMaxWidth(500);

        VBox limitsController_Ct = new VBox();
        limitsController_Ct.getChildren().addAll(limit_Ct,limit_Bt_Ct);
        limitsController_Ct.setSpacing(40);
        limitsController_Ct.setAlignment(Pos.CENTER);

        setLimitPane.setCenter(limitsController_Ct);
        BorderPane.setAlignment(limitsController_Ct,Pos.CENTER);




        // back button

        Button back_bt_limitPage = new Button("Back");
        back_bt_limitPage.setMaxWidth(400);
        Image backImage_setLimit = new Image("back.png");
        ImageView backImageView_setLimit = new ImageView(backImage_setLimit);
        backImageView_setLimit.setFitWidth(30);
        backImageView_setLimit.setFitHeight(30);
        back_bt_limitPage.setGraphic(backImageView_setLimit);
        back_bt_limitPage.setGraphicTextGap(15);

        setLimitPane.setBottom(back_bt_limitPage);
        BorderPane.setAlignment(back_bt_limitPage,Pos.CENTER_LEFT);


        limit_txtIn.setText(String.valueOf(mediaRental.getLimitedPlanLimit()));


        //actions
        back_bt_limitPage.setOnAction(e-> {
            stage.setScene(rentPage);

            limit_txtIn.setEditable(false);
            editBtLimit.setVisible(true);
            editBtLimit.setDisable(false);

            setLimit.setVisible(false);
            setLimit.setDisable(true);

        });
        editBtLimit.setOnAction(e->{
            limit_txtIn.setEditable(true);

            editBtLimit.setVisible(false);
            editBtLimit.setDisable(true);

            setLimit.setVisible(true);
            setLimit.setDisable(false);

        });

        setLimit.setOnAction(e->{

            String limitString = limit_txtIn.getText().trim();
            try {
                int limit = Integer.parseInt(limitString);
                mediaRental.setLimitedPlanLimit(limit);
            }catch (Exception exception){
                showAlert("Error","Please Enter a valid Number",1);
            }
            limit_txtIn.setEditable(false);
            editBtLimit.setVisible(true);
            editBtLimit.setDisable(false);

            setLimit.setVisible(false);
            setLimit.setDisable(true);

        });



//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        //adding icon to the stage
        Image logo = new Image("logo.png");
        stage.getIcons().add(logo);

        stage.setMaximized(true);     //maximize the stage size when opens
        stage.setTitle("Omar Media Rental");
        stage.setScene(mainPage);
        stage.show();

    }



    public static void showAlert(String titleBar , String infoMessage, int x){    //  x = 0 -> no error \\  x = 1  -> error \\ x = 2 -> warning

        Alert alert  = new Alert(ERROR);
        alert.getDialogPane().setStyle("-fx-text-alignment: Center ; -fx-font-size: 11; -fx-font-family: 'Arial Black' ");
        alert.setTitle(titleBar);
        alert.setContentText(infoMessage);

        if (x == 0){
            alert.setAlertType(CONFIRMATION);
            Image smileFace = new Image("smile.png");
            ImageView smileFaceView = new ImageView(smileFace);
            smileFaceView.setFitWidth(30);
            smileFaceView.setFitHeight(30);
            alert.setGraphic(smileFaceView);
        }else if(x == 1) {
            Image sadFace = new Image("sad.png");
            ImageView sadFaceImage = new ImageView(sadFace);
            sadFaceImage.setFitWidth(30);
            sadFaceImage.setFitHeight(30);
            alert.setGraphic(sadFaceImage);

        }else if(x == 2){
            alert.setAlertType(WARNING);
            Image sadFace = new Image("sad.png");
            ImageView sadFaceImage = new ImageView(sadFace);
            sadFaceImage.setFitWidth(30);
            sadFaceImage.setFitHeight(30);
            alert.setGraphic(sadFaceImage);
        }
        alert.showAndWait();
    }

    public static int searchCusById(String id){     //find the customer and return index
        for (int i = 0; i < mediaRental.getCustomers().size(); i++) {
            if (id.trim().equalsIgnoreCase(mediaRental.getCustomers().get(i).getId().trim()))
                return i;
        }
        return -1;
    }
    public static int searchMediaByCode(String code){     //find the media and return index
        for (int i = 0; i < mediaRental.getMediaList().size(); i++) {
            if (code.trim().equalsIgnoreCase(mediaRental.getMediaList().get(i).getCode().trim()))
                return i;
        }
        return -1;
    }

    private static void updateAndSaveCustomer(){
        try {
            File customer = new File("CustomerDB.txt");
            PrintWriter printWriter = new PrintWriter(customer);
            for (int i = 0; i < mediaRental.getCustomers().size(); i++) {
                printWriter.println(mediaRental.getCustomers().get(i).getId()+":"+mediaRental.getCustomers().get(i).getName()+":"+mediaRental.getCustomers().get(i).getMobileNum()+":"+mediaRental.getCustomers().get(i).getAddress()+":"+mediaRental.getCustomers().get(i).getPlan());
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }      //done
    private static void updateAndSaveMedia(){
        try {
            File media = new File("MediaDB.txt");
            PrintWriter printWriter = new PrintWriter(media);
            for (int i = 0; i < mediaRental.getMediaList().size(); i++) {
                if (mediaRental.getMediaList().get(i) instanceof Movie){
                    printWriter.println("Movie:"+mediaRental.getMediaList().get(i).code+":"+mediaRental.getMediaList().get(i).title+":"+mediaRental.getMediaList().get(i).getNumOfCopies()+":"+((Movie) mediaRental.getMediaList().get(i)).getRating());
                }else if (mediaRental.getMediaList().get(i) instanceof Album){
                    printWriter.println("Album:"+mediaRental.getMediaList().get(i).code+":"+mediaRental.getMediaList().get(i).title+":"+mediaRental.getMediaList().get(i).getNumOfCopies()+":"+((Album) mediaRental.getMediaList().get(i)).getArtist()+":"+(((Album) mediaRental.getMediaList().get(i)).getSongs().toString()));
                }else if (mediaRental.getMediaList().get(i) instanceof Game){
                    printWriter.println("Game:"+mediaRental.getMediaList().get(i).code+":"+mediaRental.getMediaList().get(i).title+":"+mediaRental.getMediaList().get(i).getNumOfCopies()+":"+((Game) mediaRental.getMediaList().get(i)).getWeight());
                }
            }
            printWriter.close();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }         //done
    private static void updateAndSaveRentedAndCart() {

        try {
            File rented = new File("RentedDB.txt");
            PrintWriter rentedWriter = new PrintWriter(rented);

            for (int i = 0; i < mediaRental.getCustomers().size(); i++) {
                if (!mediaRental.getCustomers().get(i).getRentedMediaList().isEmpty()){
                    rentedWriter.println(mediaRental.getCustomers().get(i).getName()+":"+mediaRental.getCustomers().get(i).getRentedMediaList());
                }
            }
            rentedWriter.close();
            updateAndSaveCart();


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }       //done
    private static void updateAndSaveCart(){
        try {
            File notRented = new File("Carts.txt");
            PrintWriter nRentedWriter = new PrintWriter(notRented);

            for (int i = 0; i < mediaRental.getCustomers().size(); i++) {
                if (!mediaRental.getCustomers().get(i).getInterestedMediaList().isEmpty()){
                    nRentedWriter.println(mediaRental.getCustomers().get(i).getName()+":"+mediaRental.getCustomers().get(i).getInterestedMediaList());
                }
            }
            nRentedWriter.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }          //done
    private static void loadData(){
        try {
            File cusInput = new File("CustomerDB.txt");
            Scanner cusReader = new Scanner(cusInput);
            while (cusReader.hasNextLine() ){
                String[] cus = cusReader.nextLine().split(":");
                if(cus.length == 5){
                    mediaRental.addCustomer(cus[0],cus[1],cus[2],cus[3],cus[4]);
                }
            }
            cusReader.close();

            File mediaInput = new File("MediaDB.txt");
            Scanner mediaReader = new Scanner(mediaInput);
            while (mediaReader.hasNextLine()){
                String[] media = mediaReader.nextLine().split(":");
                if (media[0].equalsIgnoreCase("Movie")){
                    mediaRental.addMovie(media[1],media[2], Integer.parseInt(media[3]),media[4]);
                }else if (media[0].equalsIgnoreCase("Album")){
                    mediaRental.addAlbum(media[1],media[2],Integer.parseInt(media[3]),media[4],media[5].replaceAll("\\[", "").replaceAll("]","").trim() );
                }else if (media[0].equalsIgnoreCase("Game")){
                    mediaRental.addGame(media[1],media[2],Integer.parseInt((media[3])) , Double.parseDouble(media[4]));
                }
            }
            mediaReader.close();

            File rentedIn = new File("RentedDB.txt");
            Scanner rentedReader = new Scanner(rentedIn);
            while (rentedReader.hasNextLine()){
                String[] rented = rentedReader.nextLine().split(":");
                for (int i = 0; i < mediaRental.getCustomers().size(); i++) {
                    if (rented[0].trim().equalsIgnoreCase(mediaRental.getCustomers().get(i).getName().trim())){
                        mediaRental.getCustomers().get(i).setRentedMediaList(new ArrayList<>(Arrays.asList(rented[1].replaceAll("\\[", "").replaceAll("]","").trim().split(","))));
                    }
                }

            }
            rentedReader.close();

            File cartIn = new File("Carts.txt");
            Scanner cartReader = new Scanner(cartIn);
            while (cartReader.hasNextLine()){
                String[] notRented = cartReader.nextLine().split(":");
                for (int i = 0; i < mediaRental.getCustomers().size(); i++) {
                    if (notRented[0].trim().equalsIgnoreCase(mediaRental.getCustomers().get(i).getName().trim())){
                        mediaRental.getCustomers().get(i).setInterestedMediaList(new ArrayList<>(Arrays.asList(notRented[1].replaceAll("\\[", "").replaceAll("]","").trim().split(","))));
                    }
                }
            }
            cartReader.close();

        } catch (FileNotFoundException e) {
            showAlert("DB Files Not Found" ,"DataBase Files Not Found\n - Dont Worry, Empty files will be created ",2);
        }
    }                   //done


    public static void main(String[] args) {
        launch();
    }
}


//Author: Omar Qalalweh
