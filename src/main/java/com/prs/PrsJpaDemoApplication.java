package com.prs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.math.BigDecimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.prs.business.*;
import com.prs.db.*;
import com.prs.util.Console;

@SpringBootApplication
public class PrsJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrsJpaDemoApplication.class, args);

		System.out.println("Hello\n\n");

		initialSetup();
		Console.anyKeyToContinue("\n\nInitial Setup complete.");

		userFunctions();
		Console.anyKeyToContinue("\n\nUser functions complete.");
		System.out.println("==========================================");

		vendorFunctions();
		Console.anyKeyToContinue("\n\nVendor functions complete.");
		System.out.println("==========================================");

		productFunctions();
		Console.anyKeyToContinue("\n\nProduct functions complete.");
		System.out.println("==========================================");

		getAllPurchaseRequests();
		Console.anyKeyToContinue("\n\nPurchase Request functions complete.");
		System.out.println("==========================================");

		getAllPurchaseRequestLineItems();
		Console.anyKeyToContinue("\n\nLine Item functions complete.");
		System.out.println("==========================================");

		System.out.println("Goodbye");
	}

	private static void initialSetup() {
		// Add purchase requests
		System.out.println("\n ADDING SOME PURCHASE REQUESTS\n");
		User u2 = getSingleUser("mccanndt91@gmail.com");
		// Item 1
		PurchaseRequest pr = new PurchaseRequest(u2, "This is a for an Ipad", "No justification",
				LocalDate.of(2019, Month.JULY, 1), "UPS Ground", "Received", new BigDecimal("890.97"),
				LocalDateTime.now(), null);
		PurchaseRequestDB.insert(pr);

		// Add line items
		System.out.println("\n ADDING SOME LINE ITEMS\n");
		Product p = ProductDB.getProductByID(1);
		// Item 1
		PurchaseRequestLineItem li = new PurchaseRequestLineItem(pr, p, 3);
		PurchaseRequestLineItemDB.insert(li);
		System.out.println("New line items have been added.");
	}

	private static void userFunctions() {
		getAllUsers();

		getSingleUser("mccanndt91@gmail.com");

		// Add user
		System.out.println("\n ADD A USER\n");
		User u1 = new User("test", "test", "FirstTest", "LastTest", "555-555-5555", "test@changeme.com", true, false);
		UserDB.insert(u1);
		System.out.println("New user has been added.");
		getSingleUser(u1.getEmail());

		// Update user
		System.out.println("\n UPDATE A USER\n");
		u1.setFirstName("Catbug");
		UserDB.update(u1);
		System.out.println(u1.getUserName() + "'s first name has been updated to " + u1.getFirstName());
		getSingleUser(u1.getEmail());

		// Delete user
		System.out.println("\n DELETE A USER\n");
		UserDB.delete(u1);
		System.out.println("User " + u1.getUserName() + " has been deleted.");
		getAllUsers();
	}

	private static void getAllUsers() {
		// Get all user
		List<User> users = UserDB.getAll();
		System.out.println("\n GET ALL USERS\n");
		if (users != null) {
			for (User u : users) {
				System.out.println(u.getFirstName() + " " + u.getLastName() + "\t" + u.getUserName());
			}
		} else {
			System.out.println("Users null");
		}
	}

	private static User getSingleUser(String email) {
		// Get single user
		System.out.println("\n GET SINGLE USER\n");
		User u = UserDB.getUserByEmail(email);
		if (u != null) {
			System.out.println(u.getFirstName() + " " + u.getLastName() + "\t" + u.getUserName());
			return u;
		} else {
			System.out.println("User is null");
			return null;
		}
	}

	private static void vendorFunctions() {
		getAllVendors();

		getSingleVendor("BB-1001");

		// Add vendor
		System.out.println("\n ADD A VENDOR\n");
		Vendor v1 = new Vendor("TS-9999", "TestVendor", "123 Main", "TestCity", "TT", "12345", "555-555-5555",
				"vendor@email.com", true);
		VendorDB.insert(v1);
		System.out.println(v1.getCode() + " has been added.");

		// Update user
		System.out.println("\n UPDATE A VENDOR\n");
		v1.setName("UpdatedVendorName");
		VendorDB.update(v1);
		System.out.println(v1.getCode() + " name has been updated to " + v1.getName());
		getSingleVendor(v1.getCode());

		// Delete user
		System.out.println("\n DELETE A VENDOR\n");
		VendorDB.delete(v1);
		System.out.println(v1.getCode() + " has been deleted.");
		getAllVendors();
	}

	private static void getAllVendors() {
		// Get all vendors
		System.out.println("\n GET ALL VENDORS\n");
		List<Vendor> vendors = VendorDB.getAll();
		if (vendors != null) {
			for (Vendor v : vendors) {
				System.out.println(v.getName() + "\t" + v.getCode() + "\t" + v.getEmail());
			}
		} else {
			System.out.println("Vendors null");
		}
	}

	private static void getSingleVendor(String code) {
		// Get single vendor
		System.out.println("\n GET SINGLE VENDOR\n");
		Vendor v = VendorDB.getVendorByCode(code);
		if (v != null) {
			System.out.println(v.getName() + "\t" + v.getCode() + "\t" + v.getEmail());
		} else {
			System.out.println("Vendor is null");
		}
	}

	private static void productFunctions() {
		getAllProducts();

		getSingleProduct(1);

	}

	private static void getAllProducts() {
		// Get all products
		System.out.println("\n GET ALL PRODUCTS\n");
		List<Product> products = ProductDB.getAll();
		if (products != null) {
			for (Product p : products) {
				System.out.println(p.getName() + "\t Vendor: " + p.getVendor().getName());
			}
		} else {
			System.out.println("Products null");
		}
	}

	private static void getSingleProduct(int id) {
		// Get single product
		System.out.println("\n GET SINGLE PRODUCT\n");
		Product p = ProductDB.getProductByID(id);
		if (p != null) {
			System.out.println(p.getName() + "\t Vendor: " + p.getVendor().getName());
		} else {
			System.out.println("Product is null");
		}
	}

	private static void getAllPurchaseRequests() {
		// Get all purchase requests
		System.out.println("\n GET ALL PURCHASE REQUESTS\n");
		List<PurchaseRequest> purchaseRequests = PurchaseRequestDB.getAll();
		if (purchaseRequests != null) {
			for (PurchaseRequest p : purchaseRequests) {
				System.out.println(p.getId() + " Desc: " + p.getDescription() + " Total: " + p.getTotal()
						+ " Submitted by: " + p.getUser().getUserName());
			}
		} else {
			System.out.println("Purchase requests null");
		}
	}

	private static void getAllPurchaseRequestLineItems() {
		// Get all line items
		System.out.println("\n GET ALL LINE ITEMS\n");
		List<PurchaseRequestLineItem> purchaseRequestLineItems = PurchaseRequestLineItemDB.getAll();
		if (purchaseRequestLineItems != null) {
			for (PurchaseRequestLineItem p : purchaseRequestLineItems) {
				System.out.println("Username: " + p.getPurchaseRequest().getUser().getUserName() + " Product: "
						+ p.getProduct().getName() + " Qty: " + p.getQuantity());
			}
		} else {
			System.out.println("Purchase requests null");
		}
	}

}
