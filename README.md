# Classical-River-Crossing-Puzzle
The river crossing puzzle is a classical problem in computer science, which is mainly about some entities crossing a river with some limitations.
I have solved the problem with the following entities: boat, policeman, thief, mother, father, son1, son2, girl1, and girl2.
Father, mother, two sisters, and two brothers along with a thief and a policeman are on one side of the river, which I consider region1. All these people want to go to the other side of the river, which I consider region2.\
It's noticeable to know that the following restrictions must be enforced in the solution :
1. A maximum of two people can be in the boat at the same time.
2. The thief cannot be left alone in each region with any of the family members unless the policeman accompanies him.
3. The mother cannot be left alone with any of the boys unless the father is also present.
4. The father cannot be left alone with any of the girls unless the mother is also present.
5. Only the policeman and the parents can drive the boat.
6. The boat cannot move unless it contains one entity at least.

![image](https://github.com/Zahramalaki/Classical-River-Crossing-Puzzle/assets/120048692/c1e52245-e310-4d27-94ca-e807a5848dcb)

**This project is based on programming principles! Therefore, it's object-oriented and also it implements SOLID principles.**\
**Additionally, multi-threading has been used and we process the possible solutions in parallel with four threads.**
