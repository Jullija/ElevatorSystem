# ElevatorSystem

System kontroli wind zaprogramowany w Javie.

## Klasy:
- Directions: Enum z kierunkami, w jakie mogą jechać windy
- Elevator: klasa windy
- ElevatorSystem: główna symulacja systemu
- Request: zadanie dla windy


## Główne założenia:
- Gdy winda jedzie w górę, może zabrać wyłącznie osoby, które znajdują się powyżej lub na równi jej aktualnego poziomu i również jadą w górę.
- Gdy winda jedzie w dół, może zabrać wyłącznie osoby, które znajdują się poniżej lub na równi jej aktualnego poziomu i również jadą w dół.


## Rozwiązania techniczne:
- Każda winda ma swoją kolejkę priorytetową, w której znajdują się jej zadania. Na tej podstawie bedzie ona jechać na poszczególne piętra.
- W ElevatorSystem znajduję się dwuwymiarowa tablica (windy x piętra), na podstawie której sprawdzane są windy dla każdego zadania. Dla każdego piętra widoczne są tam windy, które aktualnie się na nim znajdują.
  
  (Przykład: dostajemy Request z piętra 3 na pietro 5. Sprawdzamy, czy na piętrze 3 jest już jakaś winda, która jedzie w górę. Gdy tak, dodajemy request dla tejże windy. Jeśli nie ma żadnej windy na tym piętrze, sprawdzamy piętro +1 (czyli 4, potem 5 itp.) i piętro -1 (czyli 2, potem 1 itp.) z poprawnym kierunkiem jazdy. Pierwsza znaleziona winda dostaje nowy Request.
- Gdy wszystkie windy jadą np. w górę, a ktoś chciałby pojechać w dół, wtedy jego "request" jest dodawany do listy. Z każdym krokiem symulacji sprawdzane jest, czy można już przypisać zadanie jakieś z wind. jeśli tak, jest ono przypisywane, jeśli nie, czeka.


## Program:
- Po włączeniu programu poprosi on o podanie liczby pięter oraz liczby wind (program przyjmuje tylko inty).
- Dla ulatwienia założyłam, że w windzie mogą zmieścić się 4 osoby.
