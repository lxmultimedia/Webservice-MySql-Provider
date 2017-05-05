package model;

import java.sql.Connection;
import java.util.ArrayList;

import dao.Database;

import dao.Project;
import dto.DataObjects;

public class DataManager {


	public ArrayList<DataObjects> getData(String qparameter) throws Exception {
		ArrayList<DataObjects> feeds = null;
		try {
			Database database= new Database();
			Connection connection = database.Get_Connection();
			Project project= new Project();
			feeds=project.GetFeeds(connection, qparameter);

		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}

}

