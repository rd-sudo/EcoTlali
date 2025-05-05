     { pkgs }:
     pkgs.mkShell {
       buildInputs = [
         pkgs.openjdk
         pkgs.maven
       ];
     }