# dogs-shipment-svc
## Setup instructions
1. Clone the repository
2. Make sure Java 23 is installed
3. Build: ./gradlew build
4. Test: ./gradlew test

## Introduction
### Scenario
Your company has been shipping dogs for dogshow competitions for the past week. The logistics so far has been entirely manual, using cards.

Each crate has a specific capacity:
- An empty crate can fit up to 2 "large" dogs side by side. A "large" dog is defined as weighing 20kg or more (inclusive).
- If a crate contains 2 "large" dogs, each "large" dog can accommodate 1 "small" dog underneath them. A "small" dog is defined as weighing less than 10kg.
- A crate is considered full when it contains 2 "large" dogs and 2 "small" dogs.

### What is needed
- This repository already has a working application, with unit tests and a CI build
- Listed below are 4 stories that need to be worked on by developer pairs. We will have 4 pairs working as part of a squad.
- Each pair will write code using TDD. One member of the pair will write a test that is expected to fail, and the other member will write code to make the test pass.

### Stories
#### Get list of crates and their statuses
As a logistics manager, I want to retrieve a summary of all crates and their statuses so that I can quickly assess the overall state of shipments.

**Acceptance Criteria:**
1. When I request a list of crates, the system should return a summary of all crates, including their IDs, statuses, and total weights.
2. If no crates exist, the system should return an empty list.
3. The response should indicate whether each crate is full or has available capacity.

**Example JSON Response:**
```json
[
  {
    "crateId": "CRATE123",
    "status": "Partially Full",
    "totalWeight": 55,
    "isFull": false
  },
  {
    "crateId": "CRATE124",
    "status": "Full",
    "totalWeight": 60,
    "isFull": true
  },
  {
    "crateId": "CRATE125",
    "status": "Empty",
    "totalWeight": 0,
    "isFull": false
  }
]
```

#### Add individual dogs to a crate
As a logistics manager, I want to add individual dogs to a crate based on their size and weight so that crates are efficiently utilized.

**Acceptance Criteria:**
1. Given an empty crate, when I add a dog weighing 20kg or more, then the dog should be added as a "large" dog.
2. Given a crate with one "large" dog, when I add another dog weighing 20kg or more, then the second dog should also be added as a "large" dog.
3. Given a crate with two "large" dogs, when I add a dog weighing less than 10kg, then the dog should be added as a "small" dog under one of the large dogs.
4. If a crate already contains two "large" dogs and two "small" dogs, then the system should return an error indicating the crate is full.
5. If a dog does not meet the weight criteria for "large" or "small," then the system should return an error indicating the dog cannot be added.
6. If the crate ID does not exist, then the system should return an error indicating the crate is invalid.
7. If the dog ID is already in the crate, then the system should return an error indicating the dog is already added.

#### Get crate and information about all contents
As a logistics manager, I want to retrieve detailed information about a specific crate so that I can verify its contents and ensure proper organization.

**Acceptance Criteria:**
1. Given a valid crate ID, when I request crate information, then the system should return the crate's ID, status, and a list of all dogs in the crate, including their IDs, weights, and classifications ("large" or "small").
2. If the crate ID does not exist, then the system should return an error indicating the crate is invalid.
3. If the crate is empty, then the system should return the crate's ID and status, with an empty list of dogs.
4. The response should include the total weight of all dogs in the crate.
5. The response should indicate whether the crate is full or has available capacity.

**Example JSON Response:**
```json
{
  "crateId": "CRATE123",
  "status": "Partially Full",
  "dogs": [
    {
      "dogId": "DOG001",
      "weight": 25,
      "classification": "large"
    },
    {
      "dogId": "DOG002",
      "weight": 22,
      "classification": "large"
    },
    {
      "dogId": "DOG003",
      "weight": 8,
      "classification": "small"
    }
  ],
  "totalWeight": 55,
  "isFull": false
}
```

#### Ship a crate
As a logistics manager, I want to mark a crate as shipped so that it is no longer available for adding dogs or retrieving detailed information.

**Acceptance Criteria:**
1. Given a valid crate ID, when I mark the crate as shipped, then the system should update the crate's status to "Shipped."
2. If the crate ID does not exist, then the system should return an error indicating the crate is invalid.
3. If the crate is already marked as "Shipped," then the system should return an error indicating the crate has already been shipped.
4. The system should return a summary of the crate, including its ID, status, and total weight.

**Example JSON Response:**
```json
{
  "crateId": "CRATE123",
  "status": "Shipped",
  "totalWeight": 55
}
```