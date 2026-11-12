// swift-tools-version: 6.0

import Foundation
import PackageDescription

// React Native resolves this package through its generated autolinking/libs directory.
let usedRootEntries: Set<String> = ["Package.swift", "ios", "spm-headers"]
let unusedRootEntries = try! FileManager.default.contentsOfDirectory(atPath: Context.packageDirectory)
    .filter { !usedRootEntries.contains($0) }
    .sorted()
let unusedIosEntries = try! FileManager.default.contentsOfDirectory(
    atPath: Context.packageDirectory + "/ios"
).filter { $0 == "generated" || $0.hasSuffix(".xcodeproj") }.map { "ios/" + $0 }

let package = Package(
    name: "RNShake",
    platforms: [.iOS("15.1")],
    products: [.library(name: "RNShake", targets: ["RNShakeTarget"])],
    dependencies: [
        .package(name: "ReactNative", path: "../../../../xcframeworks"),
        .package(name: "React-GeneratedCode", path: "../../../ios"),
    ],
    targets: [
        .target(
            name: "RNShakeTarget",
            dependencies: [
                .product(name: "ReactHeaders", package: "ReactNative"),
                .product(name: "ReactNativeHeaders", package: "ReactNative"),
                .product(name: "ReactNativeDependenciesHeaders", package: "ReactNative"),
                .product(name: "ReactAppHeaders", package: "React-GeneratedCode"),
            ],
            path: ".",
            exclude: unusedRootEntries + unusedIosEntries,
            sources: ["ios"],
            publicHeadersPath: "spm-headers",
            cSettings: [
                .define("RCT_NEW_ARCH_ENABLED", to: "1"),
                .headerSearchPath("spm-headers"),
            ],
            cxxSettings: [
                .headerSearchPath("spm-headers"),
                .define("RCT_NEW_ARCH_ENABLED", to: "1"),
                .define("DEBUG", .when(configuration: .debug)),
                .define("NDEBUG", .when(configuration: .release)),
            ],
            linkerSettings: [.linkedFramework("UIKit")]
        ),
    ],
    cxxLanguageStandard: .cxx20
)
