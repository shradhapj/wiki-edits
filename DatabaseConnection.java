package com.java.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;

import com.data.pojo.JsonObjectDetails;

import jdk.nashorn.internal.scripts.JO;

public class DatabaseConnection {

	public void insert(ArrayList<JsonObjectDetails> jsonList) {
		Connection connection =  getConnection();
		int count = 0;
		try {
			Statement statement = connection.createStatement();
			String query= "insert into edits (uniqueid, action,changesize, pagetitle, url, user) values";
			for(JsonObjectDetails jObject : jsonList){
				String date = LocalDateTime.now().toString();
				count++;
				String uniqueId = date+count;
				//String query = "insert into edits values(?,?,?,?,?,?) ";
				//Statement pstatement = connection.prepareStatement(query);
				/*pstatement.setString(1, uniqueId);
				pstatement.setString(2, jObject.getAction());
				pstatement.setLong(3, jObject.getChangeSize());
				pstatement.setString(4, jObject.getPageTitle());
				pstatement.setString(5, jObject.getUrl());
				pstatement.setString(6, jObject.getUser());*/
				String action = jObject.getAction();
				Long change_size = jObject.getChangeSize();
				String page_title = jObject.getPageTitle();
				String url = jObject.getUrl();
				String user = jObject.getUser();
				String query1 = "('"+uniqueId+"','"+action+"','"+change_size+"','"+page_title+"','"+url+"','"+user+"')";
				//System.out.println("QUERY>>>>>"+query);
				query = query +query1+",";
				//statement.addBatch(query);
			}
			//StringBuilder newString = new StringBuilder(query);
			query = query.substring(0, query.length()-1)+";";
			//query = query+";";
			System.out.println("FINAL QUERY::::"+query);
			System.out.println("INT::::"+statement.execute(query));
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException exception) {
			exception.printStackTrace();
		}
		 Connection connection = null;
		try {
			
			String url="***";
			String db_user = "***";
			String db_password = "***";
			connection = DriverManager.getConnection(url,db_user, db_password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}

}
