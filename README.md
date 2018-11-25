# RSA-implementantion-and-analysis
Repository for a coursework aiming to implement a simplified RSA algorithm and analyse it's vulnerabilities. Part of the Computer Security module @GoldsmithsUni, UoL

## This repo aims to solve 2 parts of the coursework:
### Part 1:
*Develop a software prototype in Java to demonstrate how the RSA algorithms work using the simplified algorithms and examples studied in the lectures/workshops. In particular, your prototype should demonstrate how two primes p and q are generated, how the random numbere is generated, where 0<e<r and e has no factor in common with r, and how the private key d and public key(e,n)are generated.* 

*As part of testing, a good coursework may also demonstrate a special case when your RSA program would not work securely.Your program should prompt the user to input certain parameters that would lead to the problematic state.There  is  no  specific  requirement  to  the  user  interface  of  your  prototype  but  you  should  design  at  leasta  simple  user  interface  to  allow  the  user  to  simulate  a  communication  scenario,  where  Alice  sends  an encrypted message to Bob, and Bob decrypts the ciphertext to read the message. Also, Charlie may intercept the data flow and obtain unauthorised information.*

*You may decide where to start your design but it would often be easier to first divide the task into a number of subtasks. For example:*
1.  Implement a cryptorandom key generator and the algorithm for modular exponentiation.
2.  Implement the RSA encryption algorithm.
3.  Implement the RSA decryption algorithm. <br>
*You may add if necessary assumptions for details to ease your implementation, but you must explain them clearly to gain credits.*

### Part 2: 
*Based on the software prototype that you have developed in the previous part, analyse and implement the protocol below about authentication using a trusted server S. Suppose a trusted server S that distributes public keys on behalf of others. Thus S holds Alice’s public key KA and Bob’s public key KB. S’s public key,kS, is well known.  Now Alice (A) and Bob (B) wish to authenticate with each other and they propose to use the following protocol:*
1) Dear S, This is A and I would like to get B’s public key. Yours sincerely, A.
2) Dear A, Here is B’s public key signed by me. Yours sincerely, S.
3) Dear B, This is A and I have sent you a nonce only you can read. Yours sincerely, A.
4) Dear S, This is B and I would like to get A’s public key. Yours sincerely, B.
5) Dear B, Here is A’s public key signed by me. Yours sincerely, S.
6) Dear A, Here is my nonce and yours, proving I decrypted it. Yours sincerely, B.
7) Dear B, Here is your nonce proving I decrypted it. Yours sincerely, A. <br><br>
__TASKS__: 
1.  Implement this protocol in Java to demonstrate how it works (in decimal). There is no specific requirement to the user interface of your prototype but you may like to use the same simple user interface in the previous coursework assignment.
2.  Identify and in your program demonstrate if there is an error or/and a subtle vulnerability of thisprotocol. [Hint: Consider if A uses this protocol to authenticate with a third-party Z.]
