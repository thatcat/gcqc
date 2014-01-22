package controllers;

import play.mvc.*;
import play.data.validation.*;
import java.util.*;
import models.*;

public class Application extends Controller {
    
    @Before
    static void addUser() {
        User user = connected();
        if(user != null) {
            renderArgs.put("user", user);
        }
    }
    
    static User connected() {

        if(renderArgs.get("user") != null) {
            return renderArgs.get("user", User.class);
        }
        String username = session.get("user");
        if(username != null) {
            return User.find("byUsername", username).first();
        } 

        return null;
    }
    

    // ~~

    public static void index() {
    	User user = connected();
        if(user != null) {
            //跳转到登录画面
        	if(user.isAdmin == false)
        		Logined.index();
        	else
        		Logined.order_cms();///////////////////////////////////
        }
	  flash.error("请先登录！");
		List<Post> postList = Post.all().from(0).fetch(10);   	
       render(postList);

    }

	
    public static void register(String message) {
        render(message);
    }
    
    public static void saveUser(@Valid User user, String verifyPassword) {

        User userExisted = User.find("byUsername", user.username).first();
        if(userExisted != null){
            validation.required(user.username);
            validation.equals(user.username,"1").message("This user is existed!");
            if(validation.hasErrors()) {
                String message = "用户已经存在！";
                register(message);
            }
        }
        validation.required(verifyPassword);
        validation.equals(verifyPassword, user.password).message("Your password doesn't match");
        if(validation.hasErrors()) {
            render("@register", user, verifyPassword);
         }
        user.isAdmin = false;
        user.create();
        session.put("user", user.username);
        flash.success("Welcome, " + user.username);
        index();
    }
    
    public static void login(){
        render();
    }

    public static void logined(String username, String password) {
        User user = User.find("byUsernameAndPassword", username, password).first();
        if(user != null) {
            session.put("user", user.username);
            flash.success("Welcome, " + user.username);
            index();         
        }
        // Oops
        flash.put("username", username);
        flash.error("用户不存在或者密码错误！");
        login();
    }

public static void savePost( Post post) {

		if(connected()==null)
		{
            //跳转到登录画面
            Application.login();
        }		
        
		User author=null;
        String userName = session.get("user");  
		
		 if(userName != null) {
            author=User.find("byUsername", userName).first();
        } 
	
        post.author = author;  
		post.postedAt= new Date();
                
        // Validate
        //validation.valid(post);
        //if(validation.hasErrors()) {
        //   render("@form", post);
        //}

        // Save
        post.save();     
		flash.success("Thanks for posting %s", author);
		//words_board();
		//test();
    }

public static void savePost2( Post post) {
        if(connected()==null)
		{
            //跳转到登录画面
            Application.login();
        }
        /*
		if(connected()!=null) {
		User author=null;
        String userName = session.get("user");  
		
		 if(userName != null) {
            author=User.find("byUsername", userName).first();
        } 
	
        post.author = author;  
		post.postedAt= new Date();
                
        // Validate
        //validation.valid(post);
        //if(validation.hasErrors()) {
        //   render("@form", post);
        //}

        // Save
        post.save();     
		flash.success("Thanks for posting %s", author);
		//words_board();
		//test();
		}
		*/


    }
    
    public static void logout() {
        session.clear();
        index();
    }

}