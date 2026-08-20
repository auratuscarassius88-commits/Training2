# 社員研修管理ツール

Javaの学習を目的として作成した、
社員の研修結果を登録・管理するコンソールアプリケーションです。

## 概要

社員ID・氏名・研修点数を入力し、
点数に応じて合否を判定して結果を保存します。

手入力だけでなく、テキストファイルからの一括登録にも対応しています。

## 主な機能

- 研修結果の手入力
- ファイルからの一括入力
- 入力値チェック
- 社員IDの重複チェック
- 合否判定
- 研修結果の保存
- 登録結果一覧表示
- 入力・ファイル・保存処理の例外処理

## 使用技術

- Java 21
- Git / GitHub
- Visual Studio Code

## クラス構成

Main
 └ TrainingService
     ├ TrainingValidator
     └ TrainingRepository

- Main
  - ユーザー入力・結果表示
- TrainingService
  - 研修登録処理・合否判定
- TrainingValidator
  - 入力値・業務ルールの検証
- TrainingRepository
  - 保存データの読み書き
- Employee
  - 社員情報
- TrainingResult
  - 研修結果

## 実行方法

1. Java 21をインストール
2. リポジトリをclone
3. コンパイル
4. Mainクラスを実行
