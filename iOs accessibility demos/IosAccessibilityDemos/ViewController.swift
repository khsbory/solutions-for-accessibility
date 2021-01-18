//
//  ViewController.swift
//  IosAccessibilityDemos
//
//  Created by Jeonggyu Park on 21/09/2020.
//  Copyright © 2020 Jeonggyu Park. All rights reserved.
//

import UIKit




class ViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    var demoTitles:[String] =
        ["가로 스크롤 접근성 데모", "웹뷰", "접근성 포커스 데모", "가로 스크롤 접근성 데모 2"]
    @IBOutlet weak var table: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        initTable()
    }
    
    private func initTable() {
        
        self.table.register(TableCell.self, forCellReuseIdentifier: "cell")
        self.table.delegate = self
        self.table.dataSource = self
        self.table.rowHeight = 60
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return demoTitles.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell =
            tableView.dequeueReusableCell(
                withIdentifier: "cell",
                for: indexPath
                ) as!TableCell
        cell.demoTitle = demoTitles[indexPath.row]
        cell.layoutSubviews()
        return cell
    }
    
     func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (indexPath.row == 0) {
             showScreenOnOtherStoryboard(storyboardName: "VerticalScrollDemo", viewControllerStoryboardId: "verticalScrollDemo")
        } else if (indexPath.row == 1) {
            showScreenOnOtherStoryboard(storyboardName: "WebViewDemo", viewControllerStoryboardId: "webViewDemo")
        } else if (indexPath.row == 2) {
            showScreenOnOtherStoryboard(storyboardName: "AccessibilityFocusDemo", viewControllerStoryboardId: "accessibilityFocusDemo")
        } else if (indexPath.row == 3) {
            showScreenOnOtherStoryboard(storyboardName: "VerticalScrollDemo2", viewControllerStoryboardId: "verticalScrollDemo2")
        }
    }
    
    private func showScreenOnOtherStoryboard(storyboardName:String, viewControllerStoryboardId:String) {
        let storyboard = UIStoryboard(name: storyboardName, bundle: nil)
        let viewController = storyboard.instantiateViewController(withIdentifier: viewControllerStoryboardId)
             
        self.present(viewController, animated: true, completion: nil)
    }
}

