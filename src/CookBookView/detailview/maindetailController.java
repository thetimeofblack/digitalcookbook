package CookBookView.detailview;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

public class maindetailController {
	@FXML
	private Label recipeName;
	@FXML
	private Label star1;
	@FXML
	private Label star2;
	@FXML
	private Label star3;
	@FXML
	private Label star4;
	@FXML
	private Label star5;
	@FXML
	private Label preparationTime;
	@FXML
	private Label category;
	@FXML
	private Label cookTime;
	@FXML
	private Label favourite;
	@FXML
	private Button logout;
	@FXML
	private Label user;
	@FXML
	private TextField servingperson;
	@FXML
	private Label ingredient;
	@FXML
	private ImageView delete;

	// Event Listener on Label[#favourite].onDragDetected
	@FXML
	public void choosetobeFavourite(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#logout].onAction
	@FXML
	public void logOut(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on TextField[#servingperson].onAction
	@FXML
	public void servingPerson(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Label[#ingredient].onDragDetected
	@FXML
	public void showIngredients(MouseEvent event) {
		// TODO Autogenerated
	}
}