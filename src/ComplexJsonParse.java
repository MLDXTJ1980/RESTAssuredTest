import files.payLoad;
import io.restassured.path.json.JsonPath;
public class ComplexJsonParse {

	public static void main(String[] args) {
		// print amount
		JsonPath js = new JsonPath(payLoad.CoursePrice());
        int count = js.getInt("courses.size");
        System.out.println(count);
        
        // print purchase amount 
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);
        
        // print title of the first course
        String titleFirstCourses = js.get("courses[0].title"); //js.getString("courses[0].title");
        System.out.println(titleFirstCourses);
        
        // print all course titles and their respective prices 
        for(int i=0; i<count; i++)
        {
        	String courseTitles = js.get("courses["+i+"].title");
        	System.out.println(courseTitles);
        	System.out.println(js.get("courses["+i+"].price").toString()); //**
        }
        
        // print no of copies sold by RPA Course
        for(int i=0; i<count; i++)
        {
        	String courseTitles = js.get("courses["+i+"].title");
        	System.out.println(courseTitles);
        	System.out.println(js.get("courses["+i+"].price").toString()); //**
        }
        
        for(int i=0; i<count; i++)
        {
        	String courseTitles = js.get("courses["+i+"].title");
            if(courseTitles.equalsIgnoreCase("RPA"))
            {
            	int copies=js.get("courses["+i+"].copies");
            	System.out.println(copies);
            	break;
            }
        }
	}

}
