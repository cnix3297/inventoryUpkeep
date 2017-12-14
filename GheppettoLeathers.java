/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gheppettoleathers;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 *
 * @author charles
 */
public class GheppettoLeathers extends Application {
    Stage stage;
    Scene addScene, scene, checkOutScene,cashierScene, invScene;
    Button btn, one, two, three, four, five, six, seven, eight, nine, zero, 
            amountTnd, checkOutButton,checkoutBack, cashierBack, showInv;
    RadioButton addRadioColorBlack,addRadioColorBrown, addLengthKids, addLengthAdults,
            addLengthLong,cashRadioColorBlack,cashRadioColorBrown, cashLengthKids, 
            cashLengthAdults, cashLengthLong, sizeWidthSmall, sizeWidthRegular, 
            sizeWidthThick, designRadio, designPaintedRadio, designPlainRadioButton;
    VBox addColor,addLength, cashColor, cashLength;
    ToggleGroup groupAddColor, groupAddLength, groupCashColor, groupCashLength, 
                groupAddWidth, designToggleGroup;
    int childSizeStart, childSizeStop, adultSizeStart, adultSizeStop, longSizeStart, longSizeStop;
    String[] designed = {"Football", "Hearts", "Love", "Basketball", "Baseball",
                        "Eagle", "Trucker", "Basket Weave", "Edge", 
                        "Rebel", "UGA", "GameCocks"};
    String[] designedPainted = {"Flower", "Dear Scene", "Racoon Scene", 
                                "Hunter Scene", "Fish Scene", "Horse Scene"};
    GridPane addGridPane;
    int sceneWidth, sceneHeigth;
    ArrayList<Integer> addSizeArray;
    ArrayList<Double> addWidthArray;
    ArrayList<String> addColorArray, addDesignArray;
    int[][][][] database = new int[2][designed.length + designedPainted.length + 1][24][3];
                                        //[Color][Length][Size][Width]
    
    @Override
    public void start(Stage primaryStage) {
        addColorArray = new ArrayList(0);
        addDesignArray = new ArrayList(0);
        addWidthArray = new ArrayList(0);
        addSizeArray = new ArrayList(0);
        
        
        
        
        
        childSizeStart = 18;
        childSizeStop = 26;
        adultSizeStart = 28;
        adultSizeStop = 46;
        longSizeStart = 48;
        longSizeStop = 66;
        
        stage = primaryStage;
    
        //////////////////////////////////////////////////////////////////////
        /*start screen*/
        btn = new Button("Add Inventory");
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(addScene);
                
                
            }
        });
        showInv = new Button("Show Inventory");
        showInv.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                
                
            }
        });
        //////////////////////////////////////////////////////////////////////
        /*add screen*/
       
        
        ChoiceBox<String> designBox = new ChoiceBox();
        
        designBox.getItems().addAll(designed);
        designBox.setValue("Football");
        designBox.setMinWidth(30);
        designBox.setPrefSize(100, 30);
        
        
        
        //size box
        Label design = new Label("design");
        ChoiceBox size = new ChoiceBox<>();
        getSize(adultSizeStart, adultSizeStop, size);
        size.setValue(36);
        size.setPrefSize(40, 30);
        
        
            /*create radio buttons*/
        addRadioColorBlack = new RadioButton("Black");
        addRadioColorBrown = new RadioButton("Brown");
        
        addLengthKids = new RadioButton("Kids");
        addLengthAdults = new RadioButton("Adults");
        addLengthLong = new RadioButton("Long");
        
        designPlainRadioButton = new RadioButton("Plain");
        designRadio = new RadioButton("Designed");
        designPaintedRadio = new RadioButton("Painted");
        
        
        sizeWidthSmall = new RadioButton("Skinny");
        sizeWidthRegular = new RadioButton("Regular");
        sizeWidthThick = new RadioButton("Wide");
        
           /*Group radio butons*/
         designToggleGroup = new ToggleGroup();
         designPlainRadioButton.setToggleGroup(designToggleGroup);
        designRadio.setToggleGroup(designToggleGroup);
        designPaintedRadio.setToggleGroup(designToggleGroup);
        designToggleGroup.selectToggle(designRadio);        
        designToggleGroup.selectedToggleProperty().addListener(new ChangeListener
                <Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> ov,
        Toggle old_toggle, Toggle new_toggle) {
            if (designToggleGroup.getSelectedToggle() == designRadio) {
                designBox.getItems().clear();
                designBox.getItems().addAll(designed);
                designBox.setValue("Football");
                
                
            } else if (designToggleGroup.getSelectedToggle() == designPaintedRadio){
                designBox.getItems().clear();
                designBox.getItems().addAll(designedPainted);
                designBox.setValue("Hunter Scene");
                designBox.setMinWidth(30);
            } else {
            designBox.getItems().clear();
            designBox.getItems().add("Plain");
            designBox.setValue("Plain");
            designBox.setMinWidth(30);
            }  
        }}); //Listener for the Design Toggle Group also the backbone for design Drop down
        
        
        groupAddWidth = new ToggleGroup();
        Label widthLabel = new Label("WIDTH");
        sizeWidthSmall.setToggleGroup(groupAddWidth);
        sizeWidthRegular.setToggleGroup(groupAddWidth);
        sizeWidthThick.setToggleGroup(groupAddWidth);
        sizeWidthRegular.setSelected(true);
        
        
        
        groupAddColor = new ToggleGroup();
        Label colorLabel = new Label("COLOR");
        addRadioColorBlack.setToggleGroup(groupAddColor);
        addRadioColorBrown.setToggleGroup(groupAddColor);
        addRadioColorBrown.setSelected(true);
        
        groupAddColor.selectedToggleProperty().addListener(new ChangeListener
                <Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> ov,
        Toggle old_toggle, Toggle new_toggle){
                //Using same toggle buttons for the inventory and add inventory screen
                if (stage.getScene().equals(addScene)){
                if (groupAddColor.getSelectedToggle() == addRadioColorBlack){
                designPaintedRadio.setDisable(true);
                designToggleGroup.selectToggle(designPlainRadioButton);
                }else{
                designPaintedRadio.setDisable(false);
                designToggleGroup.selectToggle(designRadio);
                }
                }else/*this is for the show inventory screen*/ {
                }
            }}); //Toggle for color radio buttons
        
        
        
        groupAddLength = new ToggleGroup();
        Label lengthLabel = new Label("LENGTH");
        addLengthKids.setToggleGroup(groupAddLength);
        addLengthAdults.setToggleGroup(groupAddLength);
        addLengthLong.setToggleGroup(groupAddLength);
        addLengthAdults.setSelected(true);
        groupAddLength.selectedToggleProperty().addListener(new ChangeListener
                <Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> ov,
        Toggle old_toggle, Toggle new_toggle) {
            if (groupAddLength.getSelectedToggle() == addLengthKids) {
                sizeWidthSmall.setDisable(true);
                sizeWidthRegular.setDisable(true);
                sizeWidthThick.setDisable(true); 
                getSize(childSizeStart, childSizeStop, size);
                size.setValue(22);
            }
            
            else if (groupAddLength.getSelectedToggle() == addLengthAdults) { 
                sizeWidthSmall.setDisable(false);
                sizeWidthRegular.setDisable(false);
                sizeWidthThick.setDisable(false);
                getSize(adultSizeStart, adultSizeStop, size);
                size.setValue(36);
                
            }
            else{
                sizeWidthSmall.setDisable(false);
                sizeWidthRegular.setDisable(false);
                sizeWidthThick.setDisable(false);
                getSize(longSizeStart, longSizeStop, size);
                size.setValue(52);
                
            
            }
            }}); /*Listener for the Length raido buttons*/
        
        
        
        
        
        TextArea beltAddOutPut = new TextArea();
        beltAddOutPut.setPrefSize(475, 300);
        beltAddOutPut.setEditable(false);
        beltAddOutPut.setText("Color " + "\t" + "Design          " 
                       + "\t" + "Size" + "  " + "Width" + "\n");
        
        
        Button addButton = new Button("ADD");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(groupAddColor.getSelectedToggle().equals(addRadioColorBlack))
                {
                addColorArray.add("Black");   
                } else{ 
                addColorArray.add("Brown"); 
                }
                
                addDesignArray.add(designBox.getValue());
                addSizeArray.add((int)size.getValue());
                
                if(groupAddWidth.getSelectedToggle() == sizeWidthSmall && 
                          groupAddLength.getSelectedToggle() != addLengthKids) {
                addWidthArray.add(1.0);
                } else if(groupAddWidth.getSelectedToggle() == sizeWidthRegular && 
                        groupAddLength.getSelectedToggle() != addLengthKids){
                addWidthArray.add(1.5);
                } else if (groupAddWidth.getSelectedToggle() == sizeWidthThick && 
                        groupAddLength.getSelectedToggle() != addLengthKids){
                addWidthArray.add(2.0);
                } else {
                addWidthArray.add(0.0);
                }
               
               
                
                 outPutTextFormattedAdd( beltAddOutPut,  addSizeArray,
     addWidthArray, addColorArray,  addDesignArray );
                
 
            }
        });/*Listener for add button*/
        
        Button addScreenDeleteButton = new Button("Delete");
        addScreenDeleteButton.setOnAction(new EventHandler<ActionEvent>(){
                 @Override
            public void handle(ActionEvent event){
                boolean check = true;
                     for (int i = addColorArray.size() - 1; i > -1; i--) {
                         if(addColorArray.get(i).equals(getColor( addColorArray,  groupAddColor, addRadioColorBlack))
                                 && addDesignArray.get(i).equals(designBox.getValue()) 
                                 && addSizeArray.get(i).equals(size.getValue())){
                                    
                           
                                    if(groupAddWidth.getSelectedToggle() == sizeWidthSmall && addWidthArray.get(i) == 1.0) {
                                           
                                            outPutTextFormattedDelete( beltAddOutPut,  addSizeArray,
                                            addWidthArray,addColorArray, addDesignArray,i);
                                           addColorArray.remove(i);
                                           addDesignArray.remove(i);
                                           addSizeArray.remove(i);
                                           addWidthArray.remove(i);
                                           check = false;
                                           break;
                                           
                                    } else if(groupAddWidth.getSelectedToggle().equals(sizeWidthRegular) && addWidthArray.get(i) == 1.5){
                                            
                                            outPutTextFormattedDelete( beltAddOutPut,  addSizeArray,
                                            addWidthArray,addColorArray, addDesignArray,i);
                                            addColorArray.remove(i);
                                            addDesignArray.remove(i);
                                            addSizeArray.remove(i);
                                            addWidthArray.remove(i);
                                            check = false;
                                            break;
                                            
                                        } else if(groupAddWidth.getSelectedToggle().equals(sizeWidthThick) && addWidthArray.get(i) == 2.0){
                                           
                                            outPutTextFormattedDelete( beltAddOutPut,  addSizeArray,
                                            addWidthArray,addColorArray, addDesignArray,i);
                                            addColorArray.remove(i);
                                            addDesignArray.remove(i);
                                            addSizeArray.remove(i);
                                            addWidthArray.remove(i);
                                            check = false;
                                            break;
                                            } else if (groupAddLength.getSelectedToggle().equals(addLengthKids) && addWidthArray.get(i) == 0.0) {
                                            
                                            outPutTextFormattedDelete( beltAddOutPut,  addSizeArray,
                                            addWidthArray,addColorArray, addDesignArray,i);
                                            addColorArray.remove(i);
                                            addDesignArray.remove(i);
                                            addSizeArray.remove(i);
                                            addWidthArray.remove(i);
                                            check = false;
                                            break;
                                            }
                                        }
                                 }
                     if (check == true){
                     beltAddOutPut.appendText("This belt has already been deleted \n");
                     }
                           
            }
            });//Listener for delete buttons
        
        Button addScreenSubmit = new Button("Submit");
        addScreenSubmit.setOnAction (new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
        int a = 0,b = 0,c = 0,d = 0;
            for (int i = 0; i < addColorArray.size(); i++) {
            //get Color
                if (addColorArray.get(i).equals("Black"))
                    a = 0;
                else
                    a = 1;
            //get Design
                for (int j = 0; j < designed.length + designedPainted.length; j++) {
                    //get Unpainted Design location
                    if (j < designed.length && addDesignArray.get(i).equals(designed[j]))
                    b = j + 1;
                    
                    else if (j>= designed.length && addDesignArray.get(i).equals(designedPainted[j-designed.length]))
                    b = j + 1;
                    //adding 1 becuase in the database array 0 will be plain
                }
                //get Size
                c = (addSizeArray.get(i) - 18) /2;
            //get Width
                if (addWidthArray.get(i).equals(1.0))
                    d = 1;
                else if (addWidthArray.get(i).equals(1.5))    
                    d = 2;
                else if (addWidthArray.get(i).equals(2.0))
                    d=3;
            
            }
        
        database[a][b][c][d] =   database[a][b][c][d] +1; 
        }});
        /*Listener for delete button on the add screen*/
        
        
        /*organize radio buttons and okay button to submit to "databse"*/
        addColor = new VBox(20,colorLabel, addRadioColorBlack, 
                            addRadioColorBrown);
        addLength = new VBox(20,lengthLabel, addLengthKids, addLengthAdults, 
                                addLengthLong, size);
        VBox addWidthVBox = new VBox(20, widthLabel, sizeWidthSmall, 
                                        sizeWidthRegular, sizeWidthThick );
        VBox addSize = new VBox(20 ,design ,designPlainRadioButton , 
                                designRadio, designPaintedRadio , designBox);
        
        
        
                
        
       // GridPane showInventory = new GridPane();
        
        
        VBox startScreen = new VBox();
        
        startScreen.getChildren().add(btn);
        startScreen.getChildren().add(showInv);
        startScreen.setPrefSize(btn.getMinWidth(), btn.getMinHeight() * 2 + 20);
        startScreen.setPadding(new Insets( (600 - btn.getMinHeight()*2 - 20)/2,0,0,750/2));
        startScreen.setSpacing(10);
        
        
        
        
        
        addGridPane = new GridPane();
        addGridPane.setPrefSize(375, 300);
        addGridPane.setPadding(new Insets(5,5,5,5));
        addGridPane.setVgap(20);
        addGridPane.setHgap(50);
        addGridPane.add(addColor, 0, 0);
        addGridPane.add(addLength, 1, 1);
        addGridPane.add(addSize, 0, 1);
        addGridPane.add(addScreenDeleteButton, 0, 4);
        addGridPane.add(addWidthVBox, 1, 0);
        addGridPane.add(addButton, 1, 4);
        addGridPane.add(addScreenSubmit,1,5);
        addGridPane.setMinWidth(40);
        beltAddOutPut.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        
         HBox finalLayout = new HBox(0,addGridPane, beltAddOutPut);
        
        
        StackPane addStack = new StackPane();
        addStack.getChildren().add(finalLayout);
        StackPane showInventoryStack = new StackPane();
        //showInventoryStack.getChildren().add();
        
        
        
        
        
        
        StackPane root = new StackPane();
        root.getChildren().add(startScreen);
          
        
        scene = new Scene(root, 750, 600);
        addScene = new Scene(addStack, 750, 600);
        Scene showInventoryScene = new Scene(showInventoryStack,750, 600);
         
        stage.setTitle("Leather.Co");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    private ChoiceBox getSize( int start, int stop, ChoiceBox size){
        size.getItems().clear();
        for (int i = start; i <= stop; i = i+2) {
            size.getItems().add(i);
            
        }
        
    
    return size;
    }
    private String getColor(ArrayList<String> addColorArray, ToggleGroup groupAddColor,RadioButton addRadioColorBlack){
        if(groupAddColor.getSelectedToggle().equals(addRadioColorBlack))
                {
                return ("Black");   
                } else{
                return ("Brown"); 
                }
    
    
    }
    private String getSpace(String n){
        if (n == "Black"){
        n = n + " ";
        }else if(n == "Brown"){
        }else{
        for (int i = n.length(); i < 12; i++) {
            n = n + "  ";
        }
        }
        return n;
    }
    
    private void outPutTextFormattedAdd(TextArea beltAddOutPut, ArrayList<Integer> addSizeArray,
    ArrayList<Double> addWidthArray,ArrayList<String> addColorArray, ArrayList<String> addDesignArray ){
   beltAddOutPut.appendText(getSpace(addColorArray.get(addColorArray.size()-1)) + "\t" + 
                          getSpace(addDesignArray.get(addColorArray.size()-1)) + "\t" + 
                          addSizeArray.get(addColorArray.size()-1) + "\t" + 
                          addWidthArray.get(addColorArray.size()-1)+ "\n");
    }
    private void outPutTextFormattedDelete(TextArea beltAddOutPut, ArrayList<Integer> addSizeArray,
    ArrayList<Double> addWidthArray,ArrayList<String> addColorArray, ArrayList<String> addDesignArray, int i) {
    beltAddOutPut.appendText("DELETED \n"
                                    + getSpace(addColorArray.get(i))
                                    + "\t" + getSpace(addDesignArray.get(i)) + "\t" 
                                    + addSizeArray.get(i) + "\t" 
                                    + addWidthArray.get(i)+ "\n");}
                
                                        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

