import numpy as np
import matplotlib.pyplot as plt


data = np.loadtxt("resMode.dat",delimiter=" ",usecols = (2,6,8))
plt.scatter(data[:,0],data[:,1])
plt.xlabel("# de threads")
plt.ylabel("Throughput (peticiones/s)")
plt.title("# de threads vs Throughput")
plt.savefig("throughput.jpg")
plt.close()

plt.scatter(data[:,0],data[:,2])
plt.xlabel("# de threads")
plt.ylabel("Tiempo promedio de respuesta (ms)")
plt.title("# de threads vs Tiempo promedio de respuesta")
plt.savefig("promedio.jpg")