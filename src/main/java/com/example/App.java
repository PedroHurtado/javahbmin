package com.example;


import java.util.UUID;



import com.example.pizza.Ingredient;
import com.example.pizza.Pizza;
import com.example.verticalslice.features.ingredients.AddIngredient;
import com.example.verticalslice.features.pizza.AddPizza;
import com.example.verticalslice.features.pizza.AddPizza.Request;





/**
 * Hello world!
 *
 */
public class App {

 
    public static void main(String[] args) {        

        addIngredient();
        addPizza();
        
    }    
    public static void addPizza(){       
        
        var sql = "select i.id from Ingredient i";
        var ingredients = Configuration.query(sql, UUID.class);       
        
        var session = Configuration.creatSession();
        
        var repository = Configuration.<Pizza>createAddRepository(session);
        var repositoryIngredient = Configuration.<Ingredient,UUID>createGetRepository(session, Ingredient.class);
       
        

        Request req = new Request(
            "carbonara", 
            "pizza buenisima", 
            "url", 
            ingredients);

        var response = AddPizza.build(repository,repositoryIngredient).add(req);
        System.out.println(response);
        Configuration.UOW(session);
    }
    public static void addIngredient(){
        var session = Configuration.creatSession();
        try{
            var request = new AddIngredient.Request("queso", 1D);            
            var repository = Configuration.<Ingredient>createAddRepository(session);
            var response = AddIngredient.build(repository).add(request);                
            Configuration.UOW(session);
            System.out.println(response);
        }
        catch(Exception ex){
            Configuration.closeSession(session);
        }
        
        
      }
   
}
