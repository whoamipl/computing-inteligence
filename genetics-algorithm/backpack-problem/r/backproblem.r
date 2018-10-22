library(genalg)

items = c("zegar", "obraz-pejzaż", "obraz-portret", "radio", "laptop", 
          "lampka nocna", "srebrne sztućce", "porcelana", "figura z brązu", "skórzana torebka", "odkurzacz")
value = c(100, 300, 200, 40, 500, 70, 100, 250, 300, 280, 300)

weight = c(7, 7, 6, 2, 5, 6, 1, 3, 10, 3, 15)

db <- data.frame(items, value, weight)

maxWeight <- 25

fitnessFunction <- function(chromosome) {
  totalValue <- chromosome %*% db$value
  totalWeight <- chromosome %*% db$weight
  
  if (totalWeight > maxWeight) 
    return(0)
  else
    return (-totalValue)
}

backPackGeneticAlgorithm <- rbga.bin(size = 11, popSize = 200, iters = 100, 
                                     mutationChance = 0.05, elitism = T, evalFunc = fitnessFunction)

summary(backPackGeneticAlgorithm, echo = T)

