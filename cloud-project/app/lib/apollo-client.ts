import { ApolloClient, InMemoryCache } from "@apollo/client";

const client = new ApolloClient({
    // Asegúrate de que esta URL sea correcta
    uri: "http://microservice-services:8085/graphql",
    cache: new InMemoryCache(),
  });

export default client;
  