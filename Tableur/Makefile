### VARIABLES ###
JC = javac
SRC = src/fr/iutfbleau/SAE/
BUILD = build/fr/iutfbleau/SAE/
JVM = java

### REGLES ESSENTIELLES ###

${BUILD}Main.class : ${SRC}Main.java ${BUILD}Fenetre.class
	${JC} -encoding UTF-8 -implicit:none -d build -classpath build:res -sourcepath src ${SRC}Main.java

${BUILD}Fenetre.class : ${SRC}Fenetre.java ${BUILD}Cellule.class ${BUILD}KeyCellule.class ${BUILD}ZoneTexte.class ${BUILD}MouseCellule.class
	${JC} -encoding UTF-8 -implicit:none -d build -classpath build:res -sourcepath src ${SRC}Fenetre.java

${BUILD}Cellule.class : ${SRC}Cellule.java ${BUILD}ArbrePrefix.class
	${JC} -encoding UTF-8 -implicit:none -d build -classpath build:res -sourcepath src ${SRC}Cellule.java

${BUILD}ArbrePrefix.class : ${SRC}ArbrePrefix.java ${BUILD}NoeudSigne.class
	${JC} -encoding UTF-8 -implicit:none -d build -classpath build:res -sourcepath src ${SRC}ArbrePrefix.java

${BUILD}MouseCellule.class : ${SRC}MouseCellule.java ${BUILD}Cellule.class ${BUILD}ZoneTexte.class
	${JC} -encoding UTF-8 -implicit:none -d build -classpath build:res -sourcepath src ${SRC}MouseCellule.java

${BUILD}ZoneTexte.class : ${SRC}ZoneTexte.java ${BUILD}Cellule.class
	${JC} -encoding UTF-8 -implicit:none -d build -classpath build:res -sourcepath src ${SRC}ZoneTexte.java

${BUILD}KeyCellule.class : ${SRC}KeyCellule.java ${BUILD}Cellule.class ${BUILD}ZoneTexte.class
	${JC} -encoding UTF-8 -implicit:none -d build -classpath build:res -sourcepath src ${SRC}KeyCellule.java

${BUILD}Noeud.class : ${SRC}Noeud.java
	${JC} -encoding UTF-8 -implicit:none -d build -classpath build:res -sourcepath src ${SRC}Noeud.java

${BUILD}NoeudSigne.class : ${SRC}NoeudSigne.java ${BUILD}Noeud.class ${BUILD}NoeudReference.class ${BUILD}NoeudDouble.class
	${JC} -encoding UTF-8 -implicit:none -d build -classpath build:res -sourcepath src ${SRC}NoeudSigne.java

${BUILD}NoeudReference.class : ${SRC}NoeudReference.java ${BUILD}Noeud.class
	${JC} -encoding UTF-8 -implicit:none -d build -classpath build:res -sourcepath src ${SRC}NoeudReference.java

${BUILD}NoeudDouble.class : ${SRC}NoeudDouble.java ${BUILD}Noeud.class
	${JC} -encoding UTF-8 -implicit:none -d build -classpath build:res -sourcepath src ${SRC}NoeudDouble.java

### REGLES OPTIONNELLES ###
all: ${BUILD}Main.class
	jar -cef fr.iutfbleau.SAE.Main tableur.jar -C build fr

run :
	${JVM} -jar tableur.jar

clean :
	-rm -f ${BUILD}*.class

mrproper : clean
	-rm -f ${BUILD}Main.class

### BUTS FACTICES ###
.PHONY : run clean mrproper
### FIN ###
