## cleaning data
library(editrules)
library(deducorrect)
dirty.iris <- read.csv("dirty_iris.csv", header = T, sep = ",")

validRecords <- nrow(subset(dirty.iris, is.finite(dirty.iris$Sepal.Length) &
             is.finite(dirty.iris$Sepal.Width) &
             is.finite(dirty.iris$Petal.Length) &
             is.finite(dirty.iris$Petal.Width)))

print(validRecords)

E <- editset(c(
  "Sepal.Length >= 0",
  "Sepal.Length <= 30", 
  "Sepal.Length > Petal.Length",
  "Sepal.Width >= 0",
  "Petal.Length >= 0", 
  "Petal.Length >= 2 * Petal.Width",
  "Petal.Width >= 0",
  "Species %in% c('setosa', 'versicolor', 'virginica')"
))

ve <- violatedEdits(E, dirty.iris)
summary(ve)
plot(ve)

correctionRules <- deducorrect::correctionRules("./correction_rules.txt")
correctedData <- deducorrect::correctWithRules(correctionRules, dirty.iris)