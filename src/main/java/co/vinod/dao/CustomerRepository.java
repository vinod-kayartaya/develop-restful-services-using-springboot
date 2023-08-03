package co.vinod.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.vinod.entity.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

}
