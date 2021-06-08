# MultithreadedQueueSimulator

A simulation application in java aiming to analyse queuing based systems for determining and minimizing clients’ waiting time.

The application should simulate (by defining a simulation time 𝑡𝑠𝑖𝑚𝑢𝑙𝑎𝑡𝑖𝑜𝑛) a series of N clients arriving for service,
entering Q queues, waiting, being served and finally leaving the queues. All clients are generated when the simulation is started, 
and are characterized by three parameters: ID (a number between 1 and N), 
𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙 (simulation time when they are ready to go to the queue; i.e. time when the client finishedshopping) and
𝑡𝑠𝑒𝑟𝑣𝑖𝑐𝑒 (time interval or duration needed to serve the client; i.e. waiting time when the client is in front of the queue). 
The application tracks the total time  spentby  every client in  the queues  and  computes  the  average  waiting  time. 
Each  client  is added to the queue with minimum waiting time when its 𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙time is greaterthan or equal tothe simulation time (𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙≥𝑡𝑠𝑖𝑚𝑢𝑙𝑎𝑡𝑖𝑜𝑛).

