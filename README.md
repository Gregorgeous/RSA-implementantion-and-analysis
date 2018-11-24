# RSA-implementantion-and-analysis
Repository for a coursework aiming to implement a simplified RSA algorithm and analyse it's vulnerabilities. Part of the Computer Security module @GoldsmithsUni, UoL

## Current task of this repository:
*Develop a software prototype in Java to demonstrate how the RSA algorithms work using the simplified algorithms and examples studied in the lectures/workshops. In particular, your prototype should demonstrate how two primes p and q are generated, how the random numbere is generated, where 0<e<r and e has no factor in common with r, and how the private key d and public key(e,n)are generated.* 

*As part of testing, a good coursework may also demonstrate a special case when your RSA program would not work securely.Your program should prompt the user to input certain parameters that would lead to the problematic state.There  is  no  specific  requirement  to  the  user  interface  of  your  prototype  but  you  should  design  at  leasta  simple  user  interface  to  allow  the  user  to  simulate  a  communication  scenario,  where  Alice  sends  an encrypted message to Bob, and Bob decrypts the ciphertext to read the message. Also, Charlie may intercept the data flow and obtain unauthorised information.*

*You may decide where to start your design but it would often be easier to first divide the task into a number of subtasks. For example:*
1.  Implement a cryptorandom key generator and the algorithm for modular exponentiation.
2.  Implement the RSA encryption algorithm.
3.  Implement the RSA decryption algorithm. <br>
*You may add if necessary assumptions for details to ease your implementation, but you must explain them clearly to gain credits.*
