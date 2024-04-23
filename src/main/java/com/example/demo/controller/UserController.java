/*
 * Made by Bharat Kumar Singh and Robin Kumar Singh
 */
package com.example.demo.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Food;
import com.example.demo.entity.Order;
import com.example.demo.entity.Table1;
import com.example.demo.entity.User;
import com.example.demo.model.Login;
import com.example.demo.service.ServiceU;
import com.example.demo.service.impl.ServiceCImpl;
import com.example.demo.service.impl.ServiceImplF;
import com.example.demo.service.impl.ServiceImplU;
import com.example.demo.service.impl.ServiceOImpl;
import com.example.demo.service.impl.ServiceTImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	ServiceImplU serv;

	@Autowired
	ServiceImplF servf;

	@Autowired
	ServiceTImpl servt;

	@Autowired
	ServiceCImpl servc;

	@Autowired
	ServiceOImpl servo;
	
	
	/*
	 * function for user and admin authentication
	 */

	@PostMapping({ "/login/loginUser" })
	public String loginUser(@ModelAttribute Login l, Model model) {
		if (serv.adminAuthentication(l)) {
			Food f = new Food();
			model.addAttribute("foodo", servf.findAllF());

			model.addAttribute("logino", l);
			System.out.println(serv.userAuthentication(l));
			return "adminFCheck";
		} else if (serv.userAuthentication(l)) {
			model.addAttribute("logino", l);
			System.out.println(serv.userAuthentication(l));
			return "userHome";
		} else {
			model.addAttribute("incorrect", true);
			return "login";
		}
	}



	
	/*
	 * function to check user for the table booking
	 */
	
	@GetMapping("/login/loginUser/utable/{email}")
	public String uTable(Model model, @PathVariable("email") String email) {

		Table1 table = new Table1();

		model.addAttribute("email", email);
		model.addAttribute("table", servt.findAllT());

		return "usertable";
	}

	/*
	 * function to check user for the table booking-2
	 */
	
	@GetMapping("/login/loginUser/bkutable/{email}/{tid}")
	public String bookutable(Model model, @PathVariable("email") String email) {

		System.out.println(email);
		List<User> user = new ArrayList<User>();
		user = serv.findAllU();

		model.addAttribute("email", email);

		return "upbook";

	}

	/*
	 * function to check user for the table booking-3
	 */
	@PostMapping("/login/loginUser/pbkutable/{email}")
	public String bookutable(Model model, @PathVariable("email") String email, @RequestParam("tid") int tid) {

		System.out.println(email);
		System.out.println(tid);

		Table1 t = new Table1();
		User u = new User();
		
		//stores table in list
		List<Table1> to = new ArrayList<Table1>();
		to = servt.findAllT();
		String st;
		for (Table1 table : to) {
			System.out.println(table);
			st = table.getStatus();
			System.out.println(st);
			
			//checks for booking if tid and table id equals  
			if (table.getTid() == tid) {
				if (st.equals("Booked")) {
					System.out.println("already booked");
					return "fail";
				}
			}
		}

		u = serv.findUEmail(email);
		
		//save table
		t.setStatus("Booked");

		Table1 t2 = new Table1(tid, u, t.getStatus());
		servt.saveTable(t2);
		return "success";
	}

	//used for returning success and seting email param
	@GetMapping("/login/loginUser/lagain/{email}")
	public String loginUser(Model model, @PathVariable("email") String email) {
		System.out.println(email);
		model.addAttribute("email", email);
		return "success";
	}

	/*
	 * function used for saving cart and order 
	 */
	
	@GetMapping({ "/login/loginUser/cart/payment/{email}/{cartId}/{uid}/{fid}/{quantity}" })
	public String cartpay(@PathVariable("cartId") int cartId, @PathVariable("uid") int uid,
			@PathVariable("fid") int fid, @PathVariable("quantity") int quantity, Model model,
			@PathVariable("email") String email) {

		
		
		LocalDate currentDate = LocalDate.now();
		System.out.println(currentDate);

		
		User u = new User();
		Food f = new Food();
		
		//getting data from database if it is present from food and user
		u = serv.findId(uid);
		f = servf.getFId(fid);

		//saving order table
		Order o = new Order(currentDate, u, f);
		servo.saveOAll(o);


		model.addAttribute("email", email);
		model.addAttribute("order", o);
		return "oformat";
	}

	/*
	 * function used for storing data in cart after getting details 
	 * from user and food 
	 */

	@GetMapping({ "/login/loginUser/cart/{email}/{cartId}/{uid}/{fid}/{quantity}" })
	public String cart(@PathVariable("cartId") int cartId, @PathVariable("uid") int uid, @PathVariable("fid") int fid,
			@PathVariable("quantity") int quantity, Model model, @PathVariable("email") String email) {

		model.addAttribute("email", email);
		
		

		User u = new User();
		Food f = new Food();
		
		//getting required data of user and food from database 
		u = serv.findId(uid);
		f = servf.getFId(fid);

		
		//saving the cart after getting data
		Cart cart = new Cart(u, quantity, f);
		servc.saveAllC(cart);
		model.addAttribute("cart", cart);
		return "cart";
	}

	/*
	 * function used for redirecting into hot-beverage items 
	 */
	@GetMapping("/login/loginUser/HotBeverage/{email}")
	public String hotBeverage(@PathVariable("email") String email, Model model, User user, Cart cart) {
		model.addAttribute("quantity", cart.getQuantity());
		model.addAttribute("email", email);
		return "HotBeverage";
	}

	/*
	 * function used to hold Hot beverage items1
	 */
	
	@PostMapping("/login/loginUser/HotBeverage/hb1/{email}")
	public String hotBeverage1(@ModelAttribute Cart cart, @PathVariable("email") String email, Model model) {
		User u = new User();
		Food f = new Food();
		
		Cart c = new Cart();

		//getting user and food data
		u = serv.findUEmail(email);

		f = servf.getFId(1);


		c.setQuantity(cart.getQuantity());
		c.setFood(f);
		c.setUser(u);

//		Cart c=new Cart(c.getCartId(),u,1,f);
//		
		if ((f == null)) {
			System.out.println("f-null");
			return "fail";
		} else if (u == null) {
			System.out.println("u-null");
			return "fail";

		} else if (cart.getQuantity() == 0) {
			System.out.println("q-null");
			return "fail";

		}

		model.addAttribute("cart", c);
		model.addAttribute("user", u);
		model.addAttribute("food", f);
		model.addAttribute("quantity", c.getQuantity());
		model.addAttribute("email", email);

		return "hb1";
	}
	
	
	
	/*
	 * function used to hold Hot beverage items2
	 */

	@PostMapping("/login/loginUser/HotBeverage/hb2/{email}")
	public String hotBeverage2(@ModelAttribute Cart cart, @PathVariable("email") String email, Model model) {
		User u = new User();
		Food f = new Food();
		System.out.println(email);
		System.out.println(cart);
		Cart c = new Cart();

		u = serv.findUEmail(email);

		f = servf.getFId(2);

		System.out.println(u);
		System.out.println(f);

		System.out.println(cart.getQuantity());
		c.setQuantity(cart.getQuantity());
		c.setFood(f);
		c.setUser(u);

//		Cart c=new Cart(c.getCartId(),u,1,f);
//		
		if ((f == null)) {
			System.out.println("f-null");
			return "fail";
		} else if (u == null) {
			System.out.println("u-null");
			return "fail";

		} else if (cart.getQuantity() == 0) {
			System.out.println("q-null");
			return "fail";

		}

		model.addAttribute("cart", c);
		model.addAttribute("user", u);
		model.addAttribute("food", f);
		model.addAttribute("quantity", c.getQuantity());
		model.addAttribute("email", email);

		return "hb1";
	}

	/*
	 * function used to hold Hot beverage items3
	 */
	@PostMapping("/login/loginUser/HotBeverage/hb3/{email}")
	public String hotBeverage3(@ModelAttribute Cart cart, @PathVariable("email") String email, Model model) {
		User u = new User();
		Food f = new Food();
		System.out.println(email);
		System.out.println(cart);
		Cart c = new Cart();

		u = serv.findUEmail(email);

		f = servf.getFId(3);

		System.out.println(u);
		System.out.println(f);

		System.out.println(cart.getQuantity());
		c.setQuantity(cart.getQuantity());
		c.setFood(f);
		c.setUser(u);

//		Cart c=new Cart(c.getCartId(),u,1,f);
//		
		if ((f == null)) {
			System.out.println("f-null");
			return "fail";
		} else if (u == null) {
			System.out.println("u-null");
			return "fail";

		} else if (cart.getQuantity() == 0) {
			System.out.println("q-null");
			return "fail";
			
		}

		model.addAttribute("cart", c);
		model.addAttribute("user", u);
		model.addAttribute("food", f);
		model.addAttribute("quantity", c.getQuantity());
		model.addAttribute("email", email);

		return "hb1";
	}
	/*
	 * function used for redirecting into cold-beverage items 
	 */

	@GetMapping("/login/loginUser/coldBeverage/{email}")
	public String coldBeverage(@PathVariable("email") String email, Model model, User user, Cart cart) {
		model.addAttribute("quantity", cart.getQuantity());
		model.addAttribute("email", email);
		return "coldBeverage";
	}

	/*
	 * function used to hold cold beverage items1
	 */
	@PostMapping("/login/loginUser/coldBeverage/hb4/{email}")
	public String coldBeverage1(@ModelAttribute Cart cart, @PathVariable("email") String email, Model model) {
		User u = new User();
		Food f = new Food();
		System.out.println(email);
		System.out.println(cart);
		Cart c = new Cart();

		u = serv.findUEmail(email);

		f = servf.getFId(4);

		System.out.println(u);
		System.out.println(f);

		System.out.println(cart.getQuantity());
		c.setQuantity(cart.getQuantity());
		c.setFood(f);
		c.setUser(u);

//		Cart c=new Cart(c.getCartId(),u,1,f);
//		
		if ((f == null)) {
			System.out.println("f-null");
			return "fail";
		} else if (u == null) {
			System.out.println("u-null");
			return "fail";

		} else if (cart.getQuantity() == 0) {
			System.out.println("q-null");
			return "fail";

		}

		model.addAttribute("cart", c);
		model.addAttribute("user", u);
		model.addAttribute("food", f);
		model.addAttribute("quantity", c.getQuantity());
		model.addAttribute("email", email);

		return "hb1";
	}
	
	/*
	 * function used to hold cold beverage items2
	 */
	@PostMapping("/login/loginUser/coldBeverage/hb5/{email}")
	public String coldBeverage2(@ModelAttribute Cart cart, @PathVariable("email") String email, Model model) {
		User u = new User();
		Food f = new Food();
		System.out.println(email);
		System.out.println(cart);
		Cart c = new Cart();

		u = serv.findUEmail(email);

		f = servf.getFId(5);

		System.out.println(u);
		System.out.println(f);

		System.out.println(cart.getQuantity());
		c.setQuantity(cart.getQuantity());
		c.setFood(f);
		c.setUser(u);

//		Cart c=new Cart(c.getCartId(),u,1,f);
		
		if ((f == null)) {
			System.out.println("f-null");
			return "fail";
		} else if (u == null) {
			System.out.println("u-null");
			return "fail";

		} else if (cart.getQuantity() == 0) {
			System.out.println("q-null");
			return "fail";

		}

		model.addAttribute("cart", c);
		model.addAttribute("user", u);
		model.addAttribute("food", f);
		model.addAttribute("quantity", c.getQuantity());
		model.addAttribute("email", email);

		return "hb1";
	}
	
	/*
	 * function used to hold cold beverage items3
	 */
	
	@PostMapping("/login/loginUser/coldBeverage/hb6/{email}")
	public String coldBeverage3(@ModelAttribute Cart cart, @PathVariable("email") String email, Model model) {
		User u = new User();
		Food f = new Food();
		System.out.println(email);
		System.out.println(cart);
		Cart c = new Cart();

		u = serv.findUEmail(email);

		f = servf.getFId(6);

		System.out.println(u);
		System.out.println(f);

		System.out.println(cart.getQuantity());
		c.setQuantity(cart.getQuantity());
		c.setFood(f);
		c.setUser(u);

//		Cart c=new Cart(c.getCartId(),u,1,f);
//		
		if ((f == null)) {
			System.out.println("f-null");
			return "fail";
		} else if (u == null) {
			System.out.println("u-null");
			return "fail";

		} else if (cart.getQuantity() == 0) {
			System.out.println("q-null");
			return "fail";

		}

		model.addAttribute("cart", c);
		model.addAttribute("user", u);
		model.addAttribute("food", f);
		model.addAttribute("quantity", c.getQuantity());
		model.addAttribute("email", email);

		return "hb1";
	}

	/*
	 * function used for redirecting into burger items 
	 */
	@GetMapping("/login/loginUser/burger/{email}")
	public String burger(@PathVariable("email") String email, Model model, User user, Cart cart) {
		model.addAttribute("quantity", cart.getQuantity());
		model.addAttribute("email", email);
		return "burger";
	}
	/*
	 * function used to hold burger items1
	 */
	@PostMapping("/login/loginUser/burger/hb7/{email}")
	public String burger1(@ModelAttribute Cart cart, @PathVariable("email") String email, Model model) {
		User u = new User();
		Food f = new Food();
		System.out.println(email);
		System.out.println(cart);
		Cart c = new Cart();

		u = serv.findUEmail(email);

		f = servf.getFId(7);

		System.out.println(u);
		System.out.println(f);

		System.out.println(cart.getQuantity());
		c.setQuantity(cart.getQuantity());
		c.setFood(f);
		c.setUser(u);

//		Cart c=new Cart(c.getCartId(),u,1,f);
//		
		if ((f == null)) {
			System.out.println("f-null");
			return "fail";
		} else if (u == null) {
			System.out.println("u-null");
			return "fail";

		} else if (cart.getQuantity() == 0) {
			System.out.println("q-null");
			return "fail";

		}

		model.addAttribute("cart", c);
		model.addAttribute("user", u);
		model.addAttribute("food", f);
		model.addAttribute("quantity", c.getQuantity());
		model.addAttribute("email", email);

		return "hb1";
	}
	/*
	 * function used to hold burger items2
	 */
	@PostMapping("/login/loginUser/burger/hb8/{email}")
	public String burger2(@ModelAttribute Cart cart, @PathVariable("email") String email, Model model) {
		User u = new User();
		Food f = new Food();
		System.out.println(email);
		System.out.println(cart);
		Cart c = new Cart();

		u = serv.findUEmail(email);

		f = servf.getFId(8);

		System.out.println(u);
		System.out.println(f);

		System.out.println(cart.getQuantity());
		c.setQuantity(cart.getQuantity());
		c.setFood(f);
		c.setUser(u);

//		Cart c=new Cart(c.getCartId(),u,1,f);
//		
		if ((f == null)) {
			System.out.println("f-null");
			return "fail";
		} else if (u == null) {
			System.out.println("u-null");
			return "fail";

		} else if (cart.getQuantity() == 0) {
			System.out.println("q-null");
			return "fail";

		}

		model.addAttribute("cart", c);
		model.addAttribute("user", u);
		model.addAttribute("food", f);
		model.addAttribute("quantity", c.getQuantity());
		model.addAttribute("email", email);

		return "hb1";
	}

	/*
	 * function used for redirecting into sandwich items 
	 */
	@GetMapping("/login/loginUser/sandwiches/{email}")
	public String sandwiches(@PathVariable("email") String email, Model model, User user, Cart cart) {
		model.addAttribute("quantity", cart.getQuantity());
		model.addAttribute("email", email);
		return "sandwiches";
	}

	/*
	 * function used to hold sandwich items1
	 */
	@PostMapping("/login/loginUser/sandwiches/hb12/{email}")
	public String sandwiches1(@ModelAttribute Cart cart, @PathVariable("email") String email, Model model) {
		User u = new User();
		Food f = new Food();
		System.out.println(email);
		System.out.println(cart);
		Cart c = new Cart();

		u = serv.findUEmail(email);

		f = servf.getFId(12);

		System.out.println(u);
		System.out.println(f);

		System.out.println(cart.getQuantity());
		c.setQuantity(cart.getQuantity());
		c.setFood(f);
		c.setUser(u);

//		Cart c=new Cart(c.getCartId(),u,1,f);
//		
		if ((f == null)) {
			System.out.println("f-null");
			return "fail";
		} else if (u == null) {
			System.out.println("u-null");
			return "fail";

		} else if (cart.getQuantity() == 0) {
			System.out.println("q-null");
			return "fail";

		}

		model.addAttribute("cart", c);
		model.addAttribute("user", u);
		model.addAttribute("food", f);
		model.addAttribute("quantity", c.getQuantity());
		model.addAttribute("email", email);

		return "hb1";
	}
	/*
	 * function used to hold sandwich items2
	 */
	@PostMapping("/login/loginUser/sandwiches/hb13/{email}")
	public String sandwiches2(@ModelAttribute Cart cart, @PathVariable("email") String email, Model model) {
		User u = new User();
		Food f = new Food();
		System.out.println(email);
		System.out.println(cart);
		Cart c = new Cart();

		u = serv.findUEmail(email);

		f = servf.getFId(13);

		System.out.println(u);
		System.out.println(f);

		System.out.println(cart.getQuantity());
		c.setQuantity(cart.getQuantity());
		c.setFood(f);
		c.setUser(u);

//		Cart c=new Cart(c.getCartId(),u,1,f);
//		
		if ((f == null)) {
			System.out.println("f-null");
			return "fail";
		} else if (u == null) {
			System.out.println("u-null");
			return "fail";

		} else if (cart.getQuantity() == 0) {
			System.out.println("q-null");
			return "fail";

		}

		model.addAttribute("cart", c);
		model.addAttribute("user", u);
		model.addAttribute("food", f);
		model.addAttribute("quantity", c.getQuantity());
		model.addAttribute("email", email);

		return "hb1";
	}

	/*
	 * function used to hold sandwich items3
	 */
	@PostMapping("/login/loginUser/sandwiches/hb14/{email}")
	public String sandwiches3(@ModelAttribute Cart cart, @PathVariable("email") String email, Model model) {
		User u = new User();
		Food f = new Food();
		System.out.println(email);
		System.out.println(cart);
		Cart c = new Cart();

		u = serv.findUEmail(email);

		f = servf.getFId(14);

		System.out.println(u);
		System.out.println(f);

		System.out.println(cart.getQuantity());
		c.setQuantity(cart.getQuantity());
		c.setFood(f);
		c.setUser(u);

//		Cart c=new Cart(c.getCartId(),u,1,f);
//		
		if ((f == null)) {
			System.out.println("f-null");
			return "fail";
		} else if (u == null) {
			System.out.println("u-null");
			return "fail";

		} else if (cart.getQuantity() == 0) {
			System.out.println("q-null");
			return "fail";

		}

		model.addAttribute("cart", c);
		model.addAttribute("user", u);
		model.addAttribute("food", f);
		model.addAttribute("quantity", c.getQuantity());
		model.addAttribute("email", email);

		return "hb1";
	}

	/*
	 * function used for redirecting into bread items 
	 */
	@GetMapping("/login/loginUser/breadIteam/{email}")
	public String breadIteam(@PathVariable("email") String email, Model model, User user, Cart cart) {
		model.addAttribute("quantity", cart.getQuantity());
		model.addAttribute("email", email);
		return "breaditeam";
	}

	/*
	 * function used to hold bread items1
	 */
	@PostMapping("/login/loginUser/breadIteam/hb9/{email}")
	public String breadIteam1(@ModelAttribute Cart cart, @PathVariable("email") String email, Model model) {
		User u = new User();
		Food f = new Food();
		System.out.println(email);
		System.out.println(cart);
		Cart c = new Cart();

		u = serv.findUEmail(email);

		f = servf.getFId(9);

		System.out.println(u);
		System.out.println(f);

		System.out.println(cart.getQuantity());
		c.setQuantity(cart.getQuantity());
		c.setFood(f);
		c.setUser(u);

//		Cart c=new Cart(c.getCartId(),u,1,f);
//		
		if ((f == null)) {
			System.out.println("f-null");
			return "fail";
		} else if (u == null) {
			System.out.println("u-null");
			return "fail";

		} else if (cart.getQuantity() == 0) {
			System.out.println("q-null");
			return "fail";

		}

		model.addAttribute("cart", c);
		model.addAttribute("user", u);
		model.addAttribute("food", f);
		model.addAttribute("quantity", c.getQuantity());
		model.addAttribute("email", email);

		return "hb1";
	}

	/*
	 * function used to hold bread items2
	 */
	@PostMapping("/login/loginUser/breadIteam/hb10/{email}")
	public String breadIteam2(@ModelAttribute Cart cart, @PathVariable("email") String email, Model model) {
		User u = new User();
		Food f = new Food();
		System.out.println(email);
		System.out.println(cart);
		Cart c = new Cart();

		u = serv.findUEmail(email);

		f = servf.getFId(10);

		System.out.println(u);
		System.out.println(f);

		System.out.println(cart.getQuantity());
		c.setQuantity(cart.getQuantity());
		c.setFood(f);
		c.setUser(u);

//		Cart c=new Cart(c.getCartId(),u,1,f);
//		
		if ((f == null)) {
			System.out.println("f-null");
			return "fail";
		} else if (u == null) {
			System.out.println("u-null");
			return "fail";

		} else if (cart.getQuantity() == 0) {
			System.out.println("q-null");
			return "fail";

		}

		model.addAttribute("cart", c);
		model.addAttribute("user", u);
		model.addAttribute("food", f);
		model.addAttribute("quantity", c.getQuantity());
		model.addAttribute("email", email);

		return "hb1";
	}

	/*
	 * function used to hold bread items3
	 */
	@PostMapping("/login/loginUser/breadIteam/hb11/{email}")
	public String breadIteam3(@ModelAttribute Cart cart, @PathVariable("email") String email, Model model) {
		User u = new User();
		Food f = new Food();
		System.out.println(email);
		System.out.println(cart);
		Cart c = new Cart();

		u = serv.findUEmail(email);

		f = servf.getFId(11);

		System.out.println(u);
		System.out.println(f);

		System.out.println(cart.getQuantity());
		c.setQuantity(cart.getQuantity());
		c.setFood(f);
		c.setUser(u);

//		Cart c=new Cart(c.getCartId(),u,1,f);
//		
		if ((f == null)) {
			System.out.println("f-null");
			return "fail";
		} else if (u == null) {
			System.out.println("u-null");
			return "fail";

		} else if (cart.getQuantity() == 0) {
			System.out.println("q-null");
			return "fail";

		}

		model.addAttribute("cart", c);
		model.addAttribute("user", u);
		model.addAttribute("food", f);
		model.addAttribute("quantity", c.getQuantity());
		model.addAttribute("email", email);

		return "hb1";
	}
	
	
	

}