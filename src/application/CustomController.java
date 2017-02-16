/*
 * Developed By
 * Alvin Chau 2/15/2017
 * Andy Phan
 * 
 */

package application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CustomController {
    @FXML
    private Button AddBtn;

    @FXML
     private Button SaveBtn;

    @FXML
    private Button DeleteBtn;

    @FXML
    private TextField nameField;
    @FXML
    private TextField artistField;
    @FXML
    private TextField albumField;
    @FXML
    private TextField yearField;


   private ObservableList<Song> songs= FXCollections.observableArrayList();

   @FXML
   private ListView<Song> listView;


    public void handleAdd(ActionEvent e)
    {
        Song s= new Song("(New Blank Song)","","","");
        ReadFile.songs.add(s); //add song to file
        songs.add(s);
        listView.setItems(songs);
        listView.getSelectionModel().select(songs.size()); //select new added song
    }
   public void handleSave(ActionEvent e)
    {
	   //name and artist must not be empty
        if("".equals(nameField.getText()) || "".equals(artistField.getText())){
            Alert alert =
                    new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You must enter a name and artist");
            alert.showAndWait();

            return;
        }
        //if encounter a song with same artist with same song name when saving edits, give an alert
            for(Song s : songs){
                if(s.getName().equals(nameField.getText()) && s.getArtist().equals(artistField.getText()))
                {
                    Alert alert =
                            new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("This song with this artist already exists!");
                    alert.showAndWait();
                    return;
                }
            }

            //assign song deets to blank song
        Song s = listView.getSelectionModel().getSelectedItem();
        
        s.setName(nameField.getText());
        s.setArtist(artistField.getText());
        s.setAlbum(albumField.getText());
        s.setYear(yearField.getText());
        FXCollections.sort(songs);

    }
    public void handleDelete(ActionEvent e)
    {
        Song s = listView.getSelectionModel().getSelectedItem();

        int nextIndex = listView.getSelectionModel().getSelectedIndex() + 1;

        //Selected song is not the last one
        if(nextIndex != songs.size()){
            listView.getSelectionModel().select(nextIndex);
        }

        songs.remove(s);
        nameField.setText("");
        artistField.setText("");
        albumField.setText("");
        yearField.setText("");
        ReadFile.songs.remove(s);
        FXCollections.sort(songs);

    }

    public void start(Stage mainStage)
    {
    	//when window is closed save the file
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                ReadFile.WriteFile();
            }
        });

        ReadFile.ReadFile();
       
        songs.addAll(ReadFile.songs);
        FXCollections.sort(songs);
       listView.setItems(songs);
       
        // select the first item
        listView.getSelectionModel().select(0);
        Song s = listView.getSelectionModel().getSelectedItem();
       
        if(s==null)
        {
        	nameField.setVisible(false);		//make fields invisible
    		artistField.setVisible(false);
    		albumField.setVisible(false);
    		yearField.setVisible(false);
        }
        //if its not null set the textfields to song fields
        else
        {
        	nameField.setText(s.getName());
            artistField.setText(s.getArtist());
            albumField.setText(s.getAlbum());
            yearField.setText(s.getAlbum());
            
        }
   
        //when a change in selection occurs, grab the song fields and place into textfield

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null) {
            	
            	nameField.setVisible(true);		//make fields visible
        		artistField.setVisible(true);
        		albumField.setVisible(true);
        		yearField.setVisible(true);
        		
                nameField.setText(newValue.getName());
                artistField.setText(newValue.getArtist());
                albumField.setText(newValue.getAlbum());
                yearField.setText(newValue.getAlbum());
                
                
            }

        });

    }


}

