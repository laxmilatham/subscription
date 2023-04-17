package com.demo.subscription;


import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    
    public void createSubscription(Subscription subscription) throws Exception {
    	System.out.println(subscription);
        // Perform data input sanitization
        String sanitizedEmail = sanitize(subscription.getEmail());
        String sanitizedName = sanitize(subscription.getName());
        String sanitizedUserType = sanitize(subscription.getUserType());
        String sanitizedCompany = sanitize(subscription.getCompany());
        String sanitizedApplicationType = sanitize(subscription.getApplicationType());
        
        System.out.println(sanitizedEmail);
        System.out.println(sanitizedName);
        System.out.println(sanitizedUserType);
        System.out.println(sanitizedCompany);
        System.out.println(sanitizedApplicationType);
        // Perform error validation
        if (StringUtils.isBlank(sanitizedEmail)) {
            throw new Exception("Email is required");
        }
        
        if (StringUtils.isBlank(sanitizedName)) {
            throw new Exception("Name is required");
        }
        
        if (!isValidUserType(sanitizedUserType)) {
            throw new Exception("User type is required");
        }
        
        if (StringUtils.isBlank(sanitizedCompany)) {
            throw new Exception("Company is required");
        }
        
        if (!isValidApplicationType(sanitizedApplicationType)) {
            throw new Exception("Application type is required");
        }
        
        if (!isValidEmail(sanitizedEmail)) {
            throw new Exception("Invalid email format");
        }
        
        // Save the subscription to the database
        // Example code:
        // subscriptionRepository.save(subscription);
    }
    
    private String sanitize(String value) {
        value = StringEscapeUtils.escapeHtml4(value);
        return StringUtils.trimToEmpty(value);
    }
    
    private boolean isValidUserType(String userType) {
        return userType.equalsIgnoreCase("Developer") || userType.equalsIgnoreCase("Marketing");
    }
    
    private boolean isValidEmail(String email) {
        // Implement email validation logic
        // Example code:
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    
    private boolean isValidApplicationType(String applicationType) {
        return applicationType.equalsIgnoreCase("Services") || applicationType.equalsIgnoreCase("WebApplication");
    }
}
