package co.vinod.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.vinod.dao.CustomerRepository;
import co.vinod.entity.Customer;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
	
	@Autowired
	private CustomerRepository repo;
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Integer id){
		try {
			Customer c1 = repo.findById(id).get();
			repo.delete(c1);
			return ResponseEntity.ok(c1);
		} catch (Exception e) {
			return ResponseEntity.status(404).body(null);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(
			@PathVariable("id") Integer id, 
			@RequestBody Customer customer) {
		customer.setId(id);
		repo.save(customer);
		return ResponseEntity.ok(customer);
	}
	
	@PostMapping
	public ResponseEntity<?> addNewCustomer(@RequestBody Customer customer) {
		repo.save(customer);
		return ResponseEntity.ok(customer);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable Integer id) {
		
		try {
			Customer c1 = repo.findById(id).get();
			return ResponseEntity.ok(c1);
		} catch (Exception e) {
			return ResponseEntity.status(404).body(null);
		}
		
	}
	
	@GetMapping
	public Iterable<Customer> getAllCustomers(
			@RequestParam(name="_page", defaultValue = "1") Integer pageNum, 
			@RequestParam(name="_limit", defaultValue = "10") Integer pageSize) {
		Pageable p = PageRequest.of(pageNum-1, pageSize);
		return repo.findAll(p).getContent();
	}

}
