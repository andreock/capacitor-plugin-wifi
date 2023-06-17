import Foundation

@objc public class CapacitorWifi: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
