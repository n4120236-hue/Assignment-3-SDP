# Assignment 3 | Bridge Pattern

- Name: Yershadi Nurassyl
- Group: SE-2523
- Topic: A (Drawing)
- Repository URL: https://github.com/n4120236-hue/Assignment-3-SDP
- Base commit (working I1/I2 version): e7b1074b829d3e49e10526177411ad3574df3fe5
- Submitted commit (I3 extension): b546192142a2f6e4685af9a50af7f0602d618336

## Role map

| Role | Class | Source path |
|---|---|---|
| Abstraction | Shape | src/bridge/Shape.java |
| A1 | Circle | src/bridge/Circle.java |
| A2 | Square | src/bridge/Square.java |
| Implementor | Renderer | src/bridge/Renderer.java |
| I1 | VectorRenderer | src/bridge/VectorRenderer.java |
| I2 | RasterRenderer | src/bridge/RasterRenderer.java |
| I3 | AsciiRenderer | src/bridge/AsciiRenderer.java |
| Client | Main | src/Main.java |

## Where to look

- Bridge field: `private Renderer renderer` in src/bridge/Shape.java, line 8
- `execute()`: abstract in Shape.java line 16, implemented in Circle.java and Square.java
- `setImplementation(Renderer)`: src/bridge/Shape.java, line 18
- T5 check: `checkRuntimeSwitch()` in src/Main.java, line 57 (called at line 42)

## Commands

```
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo
```

## Expected results

| Check | Setup | Expected |
|---|---|---|
| T1 | Circle(radius 2) + VectorRenderer | VECTOR circle radius=2 |
| T2 | Circle(radius 2) + RasterRenderer | RASTER circle radius=2 bitmap=4x4 |
| T3 | Square(side 3) + VectorRenderer | VECTOR square side=3 |
| T4 | Square(side 3) + RasterRenderer | RASTER square side=3 bitmap=3x3 |
| T5 | one Circle: Vector, then Raster | sameObject=true, stateUnchanged=true, before=VECTOR circle radius=2, after=RASTER circle radius=2 bitmap=4x4 |
| T6 | Circle(radius 2) + AsciiRenderer | ASCII (o) circle radius=2 |
| T7 | Square(side 3) + AsciiRenderer | ASCII [#] square side=3 |

Final line: SUMMARY: 7/7 PASS
