# PromQL Support

An IntelliJ IDEA plugin that provides syntax highlighting for Prometheus Query Language (PromQL).

This plugin uses [ANTLR](https://github.com/antlr/grammars-v4/blob/5660ba571209e7c28c0e36c38414729e5b6db087/promql/PromQLLexer.g4) for lexical analysis of PromQL.

## Installation

### From JetBrains Marketplace

1. Open IntelliJ IDEA
2. Go to Settings/Preferences → Plugins
3. Select the "Marketplace" tab
4. Search for "PromQL Support"
5. Click "Install"
6. Restart IntelliJ IDEA when prompted

## Usage

After installation, the plugin will automatically recognize files with the `.promql` extension and provide syntax highlighting for them. You can also create new PromQL files by:

1. Right-clicking on a directory in the Project view
2. Selecting "New" → "File"
3. Naming the file with a `.promql` extension

## Development

This plugin is open-source and contributions are welcome. If you'd like to contribute, please feel free to submit a pull request or open an issue.
