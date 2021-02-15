# Q1: Estructuras de datos utilizadas en el algoritmo
> Una vez realizado el problema del algoritmo de visibilidad, comenta qué estructuras de datos (Listas, Sets, etc) has seleccionado para resolverlo y porque las has considerado como las más adecuadas en cada caso.

## A1:
- Para recuperar datos de los CSV he escogido la colección *Set* ya que no mantiene el orden de los elementos, pues estos se obtienen de forma desordenada.
- Para agrupar los objetos *SizeDto* con el productId he escogido *Map* ya que me permite asociar cada producto a un array de *SizeDto* relacionados con el primero.
- Para ordenar los productos visibles he usado un *LinkedHashSet* ya que mantiene el orden en el que se introducen los elementos y estos no pueden estar repetidos.
***
# Q2: Complejidad temporal del algoritmo
> Una vez resuelto el algoritmo de visibilidad. ¿Qué complejidad temporal expresada en notación “O” crees que tiene? ¿Consideras que se podría mejorar de alguna manera?

## A2:
La complejidad temporal es de aproximadamente O(n<sup>6</sup>).

Considero que podría mejorarse incluyendo el ordenado de elementos en la agrupación "Collectors.toMap()" de la clase *VisibilityController*, en la línea 56. De esta forma se podrían eliminar las líneas 66-68, reduciendo la complejidad temporal.