//Counter incrementation in shared memory

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MultiThreadingExample
{
static int counter = 0;
static Semaphore semaphore = new Semaphore(1);
public static void incrementCounter()
{
try
{
semaphore.acquire();
counter++;
semaphore.release();
}
catch(InterruptedException ex)
{
Logger.getLogger(MultiThreadingExample.class.getName()).log(Level.SEVERE,null,
ex);
}
}
public static void main(String[] args)
{
Thread t1 = new Thread() {
@Override
public void run()
{
for(int i = 0;i &lt; 5000; i++)
{
incrementCounter();
}
}
};
Thread t2 = new Thread() {
@Override
public void run()
{
for(int i = 0;i &lt; 5000; i++)

{
incrementCounter();
}
}
};
t1.start();
t2.start();
while(t1.isAlive() || t2.isAlive())
{
System.out.println(&quot;Counter: &quot;+counter);
}
}
}