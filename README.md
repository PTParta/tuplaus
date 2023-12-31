# Tuplauspeli API dokumentaatio

Alla on listattu tuplauspeli API:n mahdolliset pyynnöt.

## Pelaajan luonti POST localhost:8080/pelaaja

Alla esimerkki pyynnön toiminnasta.

### Pyyntö

POST localhost:8080/pelaaja

```json
{
    "tunniste": "kalle123",
    "nimi": "Kalle Päätalo",
    "saldo": 100
}
```

### Vastaus

200 OK

"Pelaaja luotu"

## Tuplaus POST localhost:8080/tuplaus

Jos pelaajan saldo ei riitä tulee vastauksena 400 Bad Request, syy "Saldo ei riitä".

Jos annetulla tunnisteella ei löydy pelaajaa tulee vastauksena 400 Bad Request, syy "Tunnisteella ei löydy pelaajaa"

Alla esimerkit pyynnön toiminnasta.

### Pyyntö 1

onEnsimmäinenKierros:true -> pelin ensimmäinen kierros, jolloin pelaajan saldosta vähennetään panos.

POST localhost:8080/tuplaus

```json
{
    "tunniste":"kalle123",
    "panos":20,
    "valinta":"suuri",
    "onEnsimmainenKierros":true
}
```

### Vastaus 1

200 OK

```json
{
    "arvottuKortti": 8,
    "voitto": true,
    "mahdollisenVoitonSuuruus": 40,
    "pelitilinSaldo": 80
}
```

### Pyyntö 2

onEnsimmäinenKierros:false -> pelaaja on voittanut edellisen kierroksen ja päättänyt jatkaa tuplausta,
jolloin pelaajan saldosta ei vähennetä panosta.

POST localhost:8080/tuplaus

```json
{
    "tunniste":"kalle123",
    "panos":40,
    "valinta":"suuri",
    "onEnsimmainenKierros":false
}
```

### Vastaus 2

200 OK

```json
{
    "arvottuKortti": 3,
    "voitto": false,
    "mahdollisenVoitonSuuruus": 80,
    "pelitilinSaldo": 80
}
```

## Voittojen kotiutus POST localhost:8080/kotiutavoitot

Clientissa pidetään yllä tietoa pelin mahdollisen voiton suuruudesta. Kun pelaaja haluaa kotiuttaa voitot, tekee client pyynnön,
jossa on pelaajan tunnus ja kotiutettavan voiton suuruus.

Alla esimerkki pyynnön toiminnasta.

### Pyyntö

POST localhost:8080/kotiutavoitot

```json
{
    "tunniste":"kalle123",
    "voitonSuuruus":50
}
```

### Vastaus

200 OK

"Voitot kotiutettu"

## Pelaajien listaus GET localhost:8080/pelaajat

Palauttaa listan kaikista pelaajista. Alla esimerkki pyynnön toiminnasta.

### Pyyntö

GET localhost:8080/pelaajat

### Vastaus

200 OK
```json
[
    {
        "id": 1,
        "tunniste": "kalle123",
        "nimi": "Kalle Päätalo",
        "saldo": 0
    },
    {
        "id": 2,
        "tunniste": "mikko456",
        "nimi": "Mikko Mäki",
        "saldo": 1964
    }
]
```