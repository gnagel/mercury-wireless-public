# -*- encoding: utf-8 -*-

Gem::Specification.new do |s|
  s.name = %q{advanced_math}
  s.version = "0.0.5"

  s.required_rubygems_version = Gem::Requirement.new(">= 0") if s.respond_to? :required_rubygems_version=
  s.authors = [%q{Glenn Nagel}]
  s.date = %q{2011-08-28}
  s.description = %q{A simple gem for advanced and financial math calcualtions.}
  s.email = [%q{glenn@mercury-wireless.com}]
  s.extra_rdoc_files = ["History.txt", "Manifest.txt", "PostInstall.txt"]
  s.files = ["History.txt", "Manifest.txt", "PostInstall.txt", "README.rdoc", "advanced_math-0.0.4.gem", "advanced_math.gemspec", "lib/advanced_math.rb", "test/test_simple_moving_average.rb"]
  s.homepage = %q{https://github.com/gnagel/mercury-wireless-public/tree/master/ruby/advanced_math}
  s.post_install_message = %q{PostInstall.txt}
  s.rdoc_options = [%q{--main}, %q{README.rdoc}]
  s.require_paths = [%q{lib}]
  s.rubyforge_project = %q{advanced_math}
  s.rubygems_version = %q{1.8.6}
  s.summary = %q{A simple gem for advanced and financial math calcualtions.}
  s.test_files = [%q{test/test_simple_moving_average.rb}]

  if s.respond_to? :specification_version then
    s.specification_version = 3

    if Gem::Version.new(Gem::VERSION) >= Gem::Version.new('1.2.0') then
      s.add_development_dependency(%q<hoe>, ["~> 2.9"])
    else
      s.add_dependency(%q<hoe>, ["~> 2.9"])
    end
  else
    s.add_dependency(%q<hoe>, ["~> 2.9"])
  end
end
