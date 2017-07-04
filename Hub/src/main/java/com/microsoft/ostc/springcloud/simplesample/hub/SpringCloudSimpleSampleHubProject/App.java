package com.microsoft.ostc.springcloud.simplesample.hub.SpringCloudSimpleSampleHubProject;

import org.cloudfoundry.reactor.DefaultConnectionContext;
import org.cloudfoundry.reactor.client.ReactorCloudFoundryClient;
import org.cloudfoundry.reactor.tokenprovider.PasswordGrantTokenProvider;

import java.util.Arrays;

import javax.lang.model.element.VariableElement;

import org.cloudfoundry.client.v2.applications.*;
import org.cloudfoundry.client.v2.spaces.ListSpacesRequest;
import org.cloudfoundry.client.v3.Data;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String target = args[0];
        String username = args[1];
        String password = args[2];
        String instance_application_name = args[3];
        
        DefaultConnectionContext connectionContext = DefaultConnectionContext.builder()
        	.apiHost(target)
        	.build();

        PasswordGrantTokenProvider tokenProvider = PasswordGrantTokenProvider.builder()
        	.password(password)
        	.username(username)
        	.build();
        
        ReactorCloudFoundryClient client = ReactorCloudFoundryClient.builder()
        	    .connectionContext(connectionContext)
        	    .tokenProvider(tokenProvider)
        	    .build();
        
        ListApplicationsRequest request = ListApplicationsRequest.builder().addAllNames(Arrays.asList(new String[] {instance_application_name})).build();
        
        client.applicationsV2().list(request)
        .doOnError(error -> {
        	System.out.println(error);
        })
        .doOnSuccess(data -> {
        	ListApplicationsResponse Data = data;
        	System.out.println(data);
        });
    }
}
