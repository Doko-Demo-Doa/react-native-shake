require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "react-native-shake"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.homepage     = package["homepage"]
  s.license      = package["license"]
  s.authors      = package["author"]

  s.platforms    = { :ios => min_ios_version_supported }
  s.source       = { :git => "https://github.com/Doko-Demo-Doa/react-native-shake.git", :tag => "#{s.version}" }

  s.source_files = "ios/**/*.{h,m,mm,cpp}"

  if defined?(install_modules_dependencies()) != nil
    install_modules_dependencies(s)
  else
    s.dependency "React-Core"
  end
end
