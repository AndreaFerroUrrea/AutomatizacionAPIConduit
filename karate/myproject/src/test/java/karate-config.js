function fn() {
  
  var env = karate.env; // get system property 'karate.env'

  karate.log('karate.env system property was:', env);

  if (!env) {

    env = 'dev';

  }

  var config = {

    apiUrl:'https://api.realworld.io/api/'

  }
  if (env == 'dev') {

    config.userEmail= "johanaandrea1000@gmail.com",

    config.userPassword = "14Empanadas."


  } else if (env == 'e2e') {

  }

  return config;

}