package com.lightbend.hello.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.api.ServiceLocator;
import com.lightbend.lagom.javadsl.client.ConfigurationServiceLocator;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.lightbend.hello.api.HelloService;
import com.typesafe.config.Config;
import play.Environment;

/**
 * The module that binds the HelloService so that it can be served.
 */
public class HelloModule extends AbstractModule implements ServiceGuiceSupport {

  private final Environment environment;
  private final Config config;

  public HelloModule(Environment environment, Config config) {
    this.environment = environment;
    this.config = config;
  }

  @Override
  protected void configure() {
    bindService(HelloService.class, HelloServiceImpl.class);

    // Only needed to allow the sample to run from test and dist with the config Service Locator
//    if (environment.isProd()) {
      bind(ServiceLocator.class).to(ConfigurationServiceLocator.class);
//    }
  }
}
