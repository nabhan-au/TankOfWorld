import random
data = ""

for i in range(20):
    for j in range(20):
        randomed_data = random.randint(0, 3)
        data += str(randomed_data)
        if j != 19:
            data += " "
    data += "\n"


with open("map.map", "w") as f:
    f.write(data)
