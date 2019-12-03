# tesla-api-client 
[![Build Status](https://travis-ci.com/sachingorade/tesla-api-client.svg?branch=master)](https://travis-ci.com/sachingorade/tesla-api-client) 
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![codecov](https://codecov.io/gh/sachingorade/tesla-api-client/branch/master/graph/badge.svg)](https://codecov.io/gh/sachingorade/tesla-api-client)

Unofficial Java client for Tesla API

This is Tesla API client library for Java based projects. As specified above, this is unofficial and is built using 
various sources available online such as 
1. https://www.teslaapi.io/
2. https://tesla-api.timdorr.com

As of now, support is added for authentication API and some basic APIs. Following are the things in roadmap in order,
1. Adding interceptor to handle authentication and caching auth token
2. Adding more APIs,
    1. Vehicle data
    2. Mobile enabled
    3. Charge state

### Dependencies
This library uses Feign client to build API clients so that's the only dependency it has right now. All other 
dependencies are for testing purposes only. 