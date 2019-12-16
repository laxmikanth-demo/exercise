`1) What measures have you implemented to control access to infrastructure resources?`

* security group setup
* SSH key based authentication
* bastion host setup
* IAM policies (AWS)

`2) What is the role of metrics in DevOps?`

Metrics provides ability to capture the health of the system and helps notifying on unexpected behavior. 

Abilities with metrics include : 

* Rolling back the feature released; if anything goes unexpected.
* Access patterns by resource over the period.
* Ability to send alerts on unexpected behavior.

`3) What is the role of Infrastructure-as-Code in DevOps?`

This brings idempotence to your environment; which in turn provides the teams to configure production-like environments in the lower environments. 
Which helps to bring test driven development and ability to bring the expected state.

`4) Cite some advantages and disadvantages of having a daemon/network service listen on a port < 1024?`

Ports less than 1024 are reserved ports; Usually requires root permission to start the services. Which is not recommended.

Advantage will be only root users allowed to start the process.

`5) A team runs browser-based functional tests against a cloud-based application. The team also runs their browser-based tests on remote VMs as part of their CI/CD pipeline. The team frequently obtains inconsistent test results. How would you make the test results more consistent?`

Will make sure 

* Same browser with same version used for both cases and same sequence of navigation steps simulated.
* Test data/accounts (accounts in same state from business stand point) used closely matches in both environments.
* Both environments configured with same state (using Infrastructure-as-code practices)
* Same version of cloud-based application running in both environments. 


### Run the exercise

* `mvn clean install`
* `mvn exec:java` 
