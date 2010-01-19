importPackage(Packages.cbccore.motors)
importPackage(Packages.cbccore.sensors.buttons)
print('CBCJVM Javascript Test!')
var left = new Motor(0)
left.motor(100)
print('Push black button to exit!')
var button = new BlackButton()
while(button.isNotPushed()) { }
left.ao()
