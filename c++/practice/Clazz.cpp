#include "Clazz.h"

int Clazz::nextAge(){
	return age + 1;
}

long Clazz::lastMoney(){
	return money - 200;
}

void Clazz::losingWeight(double decr){
	weight = weight - decr;
}
