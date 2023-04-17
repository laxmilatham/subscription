package com.demo.subscription;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
//import com.demo.subscription.Subscription;
//import com.demo.subscription.SubscriptionService;

import javax.validation.Valid;

@RestController
@RequestMapping("/subscription")
@Validated
public class SubscriptionController {

	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> createSubscription(@Valid @RequestBody Subscription request) {

		System.out.println(request);
        SubscriptionService subscriptionService = new SubscriptionService();
        
			try {
				subscriptionService.createSubscription(request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

        // Use sanitized data for further processing
        return ResponseEntity.ok("Subscription created successfully");
    }

    // Handle validation errors
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public String handleValidationExceptions(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldError().getDefaultMessage();
    }

    
}


