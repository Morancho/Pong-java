# 🏓 Pong Java

## 📋 Descripció

Aquest projecte implementa una versió clàssica i funcional del joc **Pong** en Java. Ha estat desenvolupat com a projecte acadèmic dins l’estudi d’estructures de programació, gestió d’interfícies gràfiques i lògica de videojocs.

---

## 🚀 Característiques

- **Modes de joc:**
    - **Dos jugadors locals:**
        - Jugador esquerra controla la pala amb les fletxes amunt/avall
        - Jugador dreta controla la pala amb les tecles W i S
    - **Jugador vs ordinador:**
        - El jugador controla la pala esquerra amb les fletxes amunt/avall
        - L’ordinador controla la pala dreta amb IA bàsica

- **Pilota:**
    - Incrementa progressivament la velocitat durant la partida fins a un límit
    - La trajectòria després d’impactar amb la pala depèn de la zona de contacte (part superior o inferior)
    - El saque inicial és aleatori, però després de cada punt la pilota va cap al jugador que ha perdut

- **Sistema de puntuació:**
    - La partida acaba quan un jugador arriba als 10 punts
    - Comptador enrere animat abans d’iniciar cada partida

- **Interfície gràfica:** desenvolupada amb Swing per una experiència simple i clara

- **Efectes de so:** per impactes i punts

---

## 📁 Estructura del projecte

```
src/
  pong/
    Main.java               # Classe principal per iniciar el joc
    ui/
      PongFrame.java        # Contenidor principal amb gestió de pantalles
      MenuPanel.java        # Menú principal amb selecció de mode
      GamePanel.java        # Lògica i dibuix del joc
    util/
      GameMode.java         # Enumeració dels modes de joc
```






---

## ⚙️ Requisits

- Java 11 o superior
- Plataforma compatible amb Swing (Windows, Linux, macOS)

---

## 💻 Compilació i execució

Des de la carpeta `src/`, executa:

```bash
javac pong/Main.java pong/ui/*.java pong/util/*.java
java pong.Main
