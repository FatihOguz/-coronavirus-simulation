# -coronavirus-simulation
ITERATOR AND COMPOSITE, Model_View_Controller, Fly Weight and Mediator Design Patterns
video of repo --> https://www.youtube.com/watch?v=2tSJqeHSv6s&feature=youtu.be


You will design and implement a visual simulation of an epidemic within a
human society. You can use either Java Swing or FX.
Context
The society will be modeled as an empty 2D canvas of size 1000x600 pixels. Each
individual in this society of population Po will be modeled as a square of size 5x5 pixels on
this canvas, positioned randomly.
Each individual will move on this 2D canvas along an initially random direction, at a
constant speed of S pixels/second, and when s/he collides with another individual s/he will
spend C seconds with her/him at the collision spot to simulate social interaction with a
social distance D and then both will continue moving at once againt randomized directions.
Nobody can escape the canvas, if an individual reaches the edge of the map, her/his
movement direction should be once again randomized.
At the beginning there will be one random infected individual in the population.
Each individual will possess a numerical value indicating whether they wear a mask
(M=0.2) or not (M=1.0), their speed S \in [1,500] of movement in pixels/second, the social
distance D \in [0,9] (in pixels) that they practice when they collide with other individuals,
and how social they are in terms of C seconds \in [1,5] they spend with every individual
they collide with.
The disease will have a constant spreading factor R \in [0.5,1.0] and a constant
mortality rate Z \in [0.1, 0.9].When two individuals with coefficients C_1 and C_2 collide they stay together (at
collision position) for time C=max{C_1,C_2} to simulate interaction and then continue their
randomized courses. If another individual is in collision course with either of them in the
meantime, s/he cannot interact with them and ignores them as if they weren’t there.
Let two individuals I_1 and I_2 collide, with mask statuses M_1 and M_2, and social
distances D_1 and D_2 respectively. Let I_1 be infected and I_2 be healthy. They stay
together for a duration C=max{C_1,C_2} before parting, and the social distance between
them is D=min{D_1,D_2}. The probability of I_1 infecting I_2 is
P = min(R * (1+C/10) * M_1 * M_2 * (1-D/10),1)
- An infected individual will die after 100 * (1-Z) seconds and disappear from the canvas.
- Update the canvas every second or less; provide a timer, and show the total count of
infected, healthy, hospitalized and dead.
- Every infected individual, 25 seconds after her/his initial infection will be assumed to be
at the hospital and will be removed temporarily from the canvas. The hospital however is
assumed to have only B=Po/100 ventilators. After staying at the hospital for 10 seconds s/
he will return to the society at a random position as healthy. If the hospital ventilators are
all full the individual will remain and continue moving/infecting in the society, until a
ventilator becomes available or...s/he dies. The hospital is assumed to be able to cure all
cases.
Goals
1) Create the individuals using some design pattern. Choose wisely and justify your
decision. Some are more appropriate than others. Strive for maintenance cost reduction,
and flexibility. The user should be able to add them in bulk as well as one by one. (20
points)
2) Model and implement the interaction between individuals using the Mediator design
pattern. (25 points)
3) Make sure your GUI is multi-threaded, always responsive. Allow the user to pause and
continue the simulation. (25 points)
4) Use the producer/consumer paradigm to implement the hospital functionality and pay
attention to synchronization. (10 points)
5) Produce graphical plots of how the infected and dead count is affected across time
depending on various chosen values of R, Z, percentage of mask use and average social
distance D in the population. How do these outcomes relate to the population P? Linearly?
Exponentially? Something else? (10 points)
6) Evaluation of your submitted report in terms of clarity, comprehensiveness and
presentation. (10 points)





REPORT:

In our project, we need to control the interaction of people with each other as a community.
In these interactions, the composite design pattern helps to learn quickly and flexibly those who wear
masks and sick people in society.
I can quickly learn the possibility of collision of an individual, the mask of the person with whom he /
she interacts, social distance, and the situations of being infested with the iterator and composite design
pattern.


Model_View_Controller:
Model:(SOCIETY )
The human community can get sick, die, not find a place in the hospital, and every person's movement
must be visible in the view instantly.
Therefore, the change of this information of the members of the society should be notified to the view
section.
So I will add the Society class with observer design pattern.Society class to be Subject interface.
View:
An interface will be prepared to show the values of the number of people who died, the number of
healed patients, the number of infected people, the number of healthy people, the r factor, the z factor at
runtime, and can change them at runtime and stop the program at any time and continue from where it
left off.
Composite pattern will be applied since it will display them in the same frame.
Also , this class will show the number of people who died and the number of infected people at the
time of study in another framework (because there will no space left).
Controller : Since there is an observer design pattern between the model and the view, must be a
subject interface reference in the view.
I added methods for adding infected people, adding healty people, change z factor , and change r factor.
So i do not need controller class.
Singleton Design Pattern:(Hospital)
Since there are only 1 percent of the number of people in hospitals,I have to prevent this class from
being produced for the second time.So i use singleton design pattern.
public static Hospital getInstance(Subject society) {
if (uniqueInstance == null) {
synchronized (Hospital.class) {
if (uniqueInstance == null) {
uniqueInstance = new Hospital(society);
}
}
}
return uniqueInstance;
}Fly Weight Design Pattern:
I send information of components via list in communication between model and view. Because of
number of components is higher , to be increased cost. I do these processes in Mediator.
Mediator:
I controlled interaction situation. İf to be interaction situation ,i use timer for interaction time and
calculate probInfected. If component infected ,i use timer for hospital and death.
İ use fly weight for information of components.
All components move in Society class. Mediator prepare fly weights every move method and Subject
notify observer.
Iterator iterator =createIterator();
while(iterator.hasNext()){
Subject component = (Subject) iterator.next();
try {
boolean flag=component.getInterSection();
if(flag==false){
component.move();
}
this.Allx_coordinate.add(component.getXCoordinate());
this.Ally_coordinate.add(component.getYCoordinate());
}catch (UnsupportedOperationException e){
}
}
this.mediator.menage();
numberOfInfected=numberOfInfected();
this.notifyObservers();




