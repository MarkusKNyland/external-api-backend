Har laget en enkel tjeneste som konsumerer https://api.met.no/weatherapi/metalerts/1.1 og eksponerer data i to API'er. 

`/rss` som lister ut feeden fra https://api.met.no/weatherapi/metalerts/1.1?show=all

`/alert/{guid}` som viser et element basert på guid/cap. Fra https://api.met.no/weatherapi/metalerts/1.1/?{guid}
