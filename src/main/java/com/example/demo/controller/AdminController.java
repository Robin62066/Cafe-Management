package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Food;
import com.example.demo.entity.Table1;
import com.example.demo.entity.User;
import com.example.demo.service.impl.ServiceCImpl;
import com.example.demo.service.impl.ServiceImplF;
import com.example.demo.service.impl.ServiceImplU;
import com.example.demo.service.impl.ServiceOImpl;
import com.example.demo.service.impl.ServiceTImpl;

@Controller
public class AdminController {
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
	 * function used for checking Food items(admin) 
	 */

	@GetMapping("/login/chkFood/{email}")
	public String cFood1(Model model, @PathVariable("email") String email) {
		Food food = new Food();
		model.addAttribute("foodo", food);
//		System.out.println(servf.findAllF());

		model.addAttribute("email", email);
		return "cFood";
	}

	/*
	 * function used for checking Table(admin) 
	 */
	@GetMapping("/login/cTable/{email}")
	public String cTable(Model model, @PathVariable("email") String email) {
		Table1 table = new Table1();
		model.addAttribute("email", email);
		List<Table1> t = servt.findAllT();

		model.addAttribute("tblo", servt.findAllT());
//			System.out.println(t);
		return "cTable";
	}

	/*
	 * function used for booking Table(admin) 
	 */
	@GetMapping({ "/login/cTable/bTable" })
	public String bookTable(Model model) {
		Table1 table = new Table1();
		model.addAttribute("tbl", table);
		return "bTable";
	}

	/*
	 * function used for saving Table(admin) 
	 */
	@PostMapping({ "/login/cTable/bTable/saveTb" })
	public String ctableSaveB(@ModelAttribute Table1 t, @RequestParam("tid") Integer tid,
			@RequestParam("user") Integer uid, Model model) {
		System.out.println(uid); // ek hi value h
		User u = new User();
		u = serv.findId(uid);
		System.out.println(u);
		if (u == null) {
			return "fail";
		}
		t.setTid(tid);
		t.setUser(u);
		t.setStatus("Booked");

		servt.saveTable(t);
		return "success";
	}

	/*
	 * function used for unbook Table(admin) 
	 */
	@GetMapping({ "/login/cTable/ubTable" })
	public String unbookTable(Model model) {
		Table1 table = new Table1();
		model.addAttribute("tblo", table);
		return "ubTable";
	}

	
	/*
	 * function used for saving for unbook Table(admin) 
	 */
	@PostMapping("/login/cTable/ubTable/saveTbar")
	public String unbookTable2(@ModelAttribute Table1 table, @RequestParam("tid") int tid, Model model) {

		Table1 tbl = new Table1(tid, serv.findId(0), "Unbooked");
		servt.saveTable(tbl);
		return "success";

	}

	/*
	 * function used for Food hot coffee opreation(admin) 
	 */
	@GetMapping("/login/loginUser/chkFood/f11")
	public String cf11(@ModelAttribute Food f) {
		f.setFood_id(1);
		f.setFood_name("HOT COFFEE");
		f.setPrice(40);
		f.setDescription("normal coffee on high temperature");

		servf.saveF(f);

		return "success";

	}

	@GetMapping("/login/loginUser/chkFood/f12")
	public String cf12(@ModelAttribute Food food) {
		int i = 1;

		Cart cart = new Cart();
		List<Cart> lc = servc.getAllC(cart);

		for (Cart c : lc) {
			System.out.println(c.getFood().getFood_id());
			if (i == c.getFood().getFood_id()) {
				return "fail";
			}
		}

		boolean b = servf.deleteF(i);

		System.out.println(b);

		return "success";

	}

	/*
	 * function used for Food special coffee opreation(admin) 
	 */
	
	@GetMapping("/login/loginUser/chkFood/f21")
	public String cf21(@ModelAttribute Food f) {
		f.setFood_id(2);
		f.setFood_name("SPECIAL COFFEE");
		f.setPrice(60);
		f.setDescription("special flavoured coffee");

		servf.saveF(f);

		return "success";

	}

	@GetMapping("/login/loginUser/chkFood/f22")
	public String cf22(@ModelAttribute Food food) {
		int i = 2;

		Cart cart = new Cart();
		List<Cart> lc = servc.getAllC(cart);

		for (Cart c : lc) {
			System.out.println(c.getFood().getFood_id());
			if (i == c.getFood().getFood_id()) {
				return "fail";
			}
		}

		boolean b = servf.deleteF(i);

		System.out.println(b);
		return "success";

	}

	/*
	 * function used for Food cream hot coffee opreation(admin) 
	 */
	@GetMapping("/login/loginUser/chkFood/f31")
	public String cf31(@ModelAttribute Food f) {
		f.setFood_id(3);
		f.setFood_name("CREAM HOT COFFEE");
		f.setPrice(60);
		f.setDescription("whipped cream toppings on coffee");

		servf.saveF(f);

		return "success";

	}

	@GetMapping("/login/loginUser/chkFood/f32")
	public String cf32(@ModelAttribute Food food) {
		int i = 3;

		Cart cart = new Cart();
		List<Cart> lc = servc.getAllC(cart);

		for (Cart c : lc) {
			System.out.println(c.getFood().getFood_id());
			if (i == c.getFood().getFood_id()) {
				return "fail";
			}
		}

		boolean b = servf.deleteF(i);

		System.out.println(b);
		return "success";
	}

	/*
	 * function used for Food cold cream coffee opreation(admin) 
	 */
	@GetMapping("/login/loginUser/chkFood/f41")
	public String cf41(@ModelAttribute Food f) {
		f.setFood_id(4);
		f.setFood_name("COLD CREAM COFFEE");
		f.setPrice(50);
		f.setDescription("whipped cream topings on cold coffee");

		servf.saveF(f);

		return "success";
	}

	@GetMapping("/login/loginUser/chkFood/f42")
	public String cf42(@ModelAttribute Food food) {
		int i = 4;

		Cart cart = new Cart();
		List<Cart> lc = servc.getAllC(cart);

		for (Cart c : lc) {
			System.out.println(c.getFood().getFood_id());
			if (i == c.getFood().getFood_id()) {
				return "fail";
			}
		}

		boolean b = servf.deleteF(i);

		System.out.println(b);
		return "success";
	}

	/*
	 * function used for Food cold cream coffee(ice) opreation(admin) 
	 */
	@GetMapping("/login/loginUser/chkFood/f51")
	public String cf51(@ModelAttribute Food f) {
		f.setFood_id(5);
		f.setFood_name("COLD CREAM COFFEE WTH ICE CUBES");
		f.setPrice(60);
		f.setDescription("coffee with whipped cream as toppings and having ice inside it");

		servf.saveF(f);

		return "success";
	}

	@GetMapping("/login/loginUser/chkFood/f52")
	public String cf52(@ModelAttribute Food food) {
		int i = 5;

		Cart cart = new Cart();
		List<Cart> lc = servc.getAllC(cart);

		for (Cart c : lc) {
			System.out.println(c.getFood().getFood_id());
			if (i == c.getFood().getFood_id()) {
				return "fail";
			}
		}

		boolean b = servf.deleteF(i);

		System.out.println(b);
		return "success";
	}

	/*
	 * function used for Food chocolate milk opreation(admin) 
	 */
	@GetMapping("/login/loginUser/chkFood/f61")
	public String cf61(@ModelAttribute Food f) {
		f.setFood_id(6);
		f.setFood_name("CHOCOLATE MILK SHAKE");
		f.setPrice(70);
		f.setDescription("special chocolate soft dring with cocoa powder in it");

		servf.saveF(f);

		return "success";
	}

	@GetMapping("/login/loginUser/chkFood/f62")
	public String cf62(@ModelAttribute Food food) {
		int i = 6;

		Cart cart = new Cart();
		List<Cart> lc = servc.getAllC(cart);

		for (Cart c : lc) {
			System.out.println(c.getFood().getFood_id());
			if (i == c.getFood().getFood_id()) {
				return "fail";
			}
		}

		boolean b = servf.deleteF(i);

		System.out.println(b);
		return "success";
	}

	/*
	 * function used for Food veg burger opreation(admin) 
	 */
	@GetMapping("/login/loginUser/chkFood/f71")
	public String cf71(@ModelAttribute Food f) {
		f.setFood_id(7);
		f.setFood_name("VEG BURGER");
		f.setPrice(40);
		f.setDescription("burger with veggies in it");

		servf.saveF(f);

		return "success";
	}

	@GetMapping("/login/loginUser/chkFood/f72")
	public String cf72(@ModelAttribute Food food) {
		int i = 7;

		Cart cart = new Cart();
		List<Cart> lc = servc.getAllC(cart);

		for (Cart c : lc) {
			System.out.println(c.getFood().getFood_id());
			if (i == c.getFood().getFood_id()) {
				return "fail";
			}
		}

		boolean b = servf.deleteF(i);

		System.out.println(b);
		return "success";
	}

	/*
	 * function used for Food hamburger opreation(admin) 
	 */
	@GetMapping("/login/loginUser/chkFood/f81")
	public String cf81(@ModelAttribute Food f) {
		f.setFood_id(8);
		f.setFood_name("HAM BURGER");
		f.setPrice(60);
		f.setDescription("burger having non-veg(mutton) in it");

		servf.saveF(f);

		return "success";
	}

	@GetMapping("/login/loginUser/chkFood/f82")
	public String cf82(@ModelAttribute Food food) {
		int i = 8;

		Cart cart = new Cart();
		List<Cart> lc = servc.getAllC(cart);

		for (Cart c : lc) {
			System.out.println(c.getFood().getFood_id());
			if (i == c.getFood().getFood_id()) {
				return "fail";
			}
		}

		boolean b = servf.deleteF(i);

		System.out.println(b);
		return "success";
	}

	/*
	 * function used for Food BREAD TOAST WITH BUTTER(EACH) opreation(admin) 
	 */
	@GetMapping("/login/loginUser/chkFood/f91")
	public String cf91(@ModelAttribute Food f) {
		f.setFood_id(9);
		f.setFood_name("BREAD TOAST WITH BUTTER(EACH)");
		f.setPrice(20);
		f.setDescription("bread roasted and added with butter");

		servf.saveF(f);

		return "success";
	}

	@GetMapping("/login/loginUser/chkFood/f92")
	public String cf92(@ModelAttribute Food food) {
		int i = 9;

		Cart cart = new Cart();
		List<Cart> lc = servc.getAllC(cart);

		for (Cart c : lc) {
			System.out.println(c.getFood().getFood_id());
			if (i == c.getFood().getFood_id()) {
				return "fail";
			}
		}

		boolean b = servf.deleteF(i);

		System.out.println(b);
		return "success";
	}

	/*
	 * function used for Food BREAD with jam opreation(admin) 
	 */
	
	@GetMapping("/login/loginUser/chkFood/f101")
	public String cf101(@ModelAttribute Food f) {
		f.setFood_id(10);
		f.setFood_name("BREAD WITH JAM(EACH)");
		f.setPrice(20);
		f.setDescription("bread roasted and jam gets put in it");

		servf.saveF(f);

		return "success";
	}

	@GetMapping("/login/loginUser/chkFood/f102")
	public String cf102(@ModelAttribute Food food) {
		int i = 10;

		Cart cart = new Cart();
		List<Cart> lc = servc.getAllC(cart);

		for (Cart c : lc) {
			System.out.println(c.getFood().getFood_id());
			if (i == c.getFood().getFood_id()) {
				return "fail";
			}
		}

		boolean b = servf.deleteF(i);

		System.out.println(b);
		return "success";
	}

	/*
	 * function used for Food CHEESE TOAST(PER PLATE) opreation(admin) 
	 */
	@GetMapping("/login/loginUser/chkFood/f111")
	public String cf111(@ModelAttribute Food f) {
		f.setFood_id(11);
		f.setFood_name("CHEESE TOAST(PER PLATE)");
		f.setPrice(40);
		f.setDescription("bread roasted with cheese in it");

		servf.saveF(f);

		return "success";
	}

	@GetMapping("/login/loginUser/chkFood/f112")
	public String cf112(@ModelAttribute Food food) {
		int i = 11;

		Cart cart = new Cart();
		List<Cart> lc = servc.getAllC(cart);

		for (Cart c : lc) {
			System.out.println(c.getFood().getFood_id());
			if (i == c.getFood().getFood_id()) {
				return "fail";
			}
		}

		boolean b = servf.deleteF(i);

		System.out.println(b);
		return "success";
	}
	/*
	 * function used for Food veg sandwich opreation(admin) 
	 */
	
	@GetMapping("/login/loginUser/chkFood/f121")
	public String cf121(@ModelAttribute Food f) {
		f.setFood_id(12);
		f.setFood_name("VEG SANDWICH(PER PLATE)");
		f.setPrice(30);
		f.setDescription("sandwich with veggies in it");

		servf.saveF(f);

		return "success";
	}

	@GetMapping("/login/loginUser/chkFood/f122")
	public String cf122(@ModelAttribute Food food) {
		int i = 12;

		Cart cart = new Cart();
		List<Cart> lc = servc.getAllC(cart);

		for (Cart c : lc) {
			System.out.println(c.getFood().getFood_id());
			if (i == c.getFood().getFood_id()) {
				return "fail";
			}
		}

		boolean b = servf.deleteF(i);

		System.out.println(b);
		return "success";
	}

	/*
	 * function used for Food CHEESE SANDWICHE(PER PLATE) opreation(admin) 
	 */
	@GetMapping("/login/loginUser/chkFood/f131")
	public String cf131(@ModelAttribute Food f) {
		f.setFood_id(13);
		f.setFood_name("CHEESE SANDWICHE(PER PLATE)");
		f.setPrice(50);
		f.setDescription("sandwich with veggies and cheese in it");

		servf.saveF(f);

		return "success";
	}

	@GetMapping("/login/loginUser/chkFood/f132")
	public String cf132(@ModelAttribute Food food) {
		int i = 13;

		Cart cart = new Cart();
		List<Cart> lc = servc.getAllC(cart);

		for (Cart c : lc) {
			System.out.println(c.getFood().getFood_id());
			if (i == c.getFood().getFood_id()) {
				return "fail";
			}
		}

		boolean b = servf.deleteF(i);

		System.out.println(b);
		return "success";
	}

	/*
	 * function used for Food GRILLED SANDWICHE(PER PLATE) opreation(admin) 
	 */
	
	@GetMapping("/login/loginUser/chkFood/f141")
	public String cf141(@ModelAttribute Food f) {
		f.setFood_id(14);
		f.setFood_name("GRILLED SANDWICHE(PER PLATE)");
		f.setPrice(40);
		f.setDescription("sandwich grilled in barbeque with veggies in it");

		servf.saveF(f);

		return "success";
	}

	@GetMapping("/login/loginUser/chkFood/f142")
	public String cf142(@ModelAttribute Food food) {
		int i = 14;

		Cart cart = new Cart();
		List<Cart> lc = servc.getAllC(cart);

		for (Cart c : lc) {
			System.out.println(c.getFood().getFood_id());
			if (i == c.getFood().getFood_id()) {
				return "fail";
			}
		}

		boolean b = servf.deleteF(i);

		System.out.println(b);
		return "success";
	}
}
