@Controller
public class HiCtrl {

	@RequestMapping("hai")
	public void hai() {	
		System.out.println("Did you just said hi?");
		
		// Automatically call the view "hai"
		// As same as return "hai"
	}
}
-> it return type is void, request mapping url will be view url.





@RequestMapping("/")
public String hello(Model model) {
    model.addAttribute("test", Test);
    return "index";
}

<form th:action="@{/process}"  method="post" th:object="${test}">
<input type="text" th:field="*{value}"/>
<input type="submit" />
</form>







Thymeleaf demo
--------------
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
	<head>
		<title>Page Title</title>
	</head>
	<body>
		<h1>Hai,  <span th:text="${name}"/>
		</h1>
		<p th:text="${name}"/>
	</body>
</html>






http://localhost:8080/num?name=13450&name2=200345
@RequestMapping("num")
	public String sdfsd(int name,int name2, Model model) {	
		model.addAttribute("name", name+name2);
		return "hai";
	}



 <tr th:each="message : ${messages}">
            <td th:text="${message.id}">1</td>
            <td><a href="#" th:text="${message.title}">Title ...</a></td>
            <td th:text="${message.text}">Text ...</td>
        </tr>
		
		

<span th:text="#{welcome.message}" />
		
Simple Attributes: th:text=”${attributename}”
model.addAttribute("serverTime", dateFormat.format(new Date()));

Current time is <span th:text="${serverTime}" />
		


Project 1:
public class Student {
	private String name;
	private int sno;
	private double cgpa;
}

@RequestMapping("show")
public String showStd(Student std,Model model) {	
		System.out.println(std);
		model.addAttribute("abc", std);
		return "hai";
}

<h1>Hai, <span th:text="${abc.name}" /></h1>
<h1><span th:text="${abc.sno}" /></h1>
<h1><span th:text="${abc.cgpa}" /></h1>

http://localhost:8080/show?name=Gamer&sno=123&cgpa=4.7

Project 2:

There was an unexpected error (type=Bad Request, status=400).
Ambiguous handler methods mapped

http://localhost:8080/demo/num/123
@RequestMapping("/demo/num/{id}")
public String demo(@PathVariable("id") int id) {
}

http://localhost:8080/demo/num/
@RequestMapping("/demo/num")
public String demo1() {
}

http://localhost:8080/demo/
@RequestMapping("/demo")
public String demo2() {
}

http://localhost:8080/demo/num/Sujith/123
@RequestMapping("/demo/num/{name}/{id}")
public String demo4(@PathVariable("name") String name,@PathVariable("id") int id) {
}

========================
@Path("/library")
public class Library {

   @GET
   @Path("/book/{isbn}")
   public String getBook(@PathParam("isbn") String id) {
      // search my database and get a string representation and return it
   }
}


=======================
@RequestMapping(value = "/venue/{city}/{place}", method = "GET")
public String getVenueDetails(Venue venue, Model model) {
    // venue object will be automatically populated with city and place
}


===

http://localhost:8080/std/Sujith/111/7.5
//Student(String name, int sno, double cgpa)
@RequestMapping(value = "/std/{name}/{sno}/{cgpa}", method=RequestMethod.GET)
public String demo(Student std) {
	System.out.println(std);
	return "hello";
}
===
TemplateProcessingException

In Thymeleaf, hyperlink is wrapped inside @{} and access a model object inside ${}.

Note that in this form tag, the th:object attribute points to the name of the model object sent from Spring MVC controller.

https://mail.codejava.net/frameworks/spring-boot/spring-boot-thymeleaf-form-handling-tutorial


Method Not Allowed, status=405).


<form th:action="@{/edit2/{sno}(sno=*{sno})}" th:object="${abc}" method="POST"> 
		<input type="text" th:field="*{name}" />
		<input type="text" th:field="*{sno}" />
		<input type="text" th:field="*{cgpa}" />
		<input type="submit" value="Send" />
	</form>






