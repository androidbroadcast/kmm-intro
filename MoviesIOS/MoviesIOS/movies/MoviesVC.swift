//
//  MoviesVC.swift
//  iosApp
//
//  Created by Anna Zharkova on 18.12.2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import UIKit

class MoviesVC: UIViewController {
    private var adapter = MoviesAdapter()
    
 

    @IBOutlet weak var listView: UITableView!
    override func viewDidLoad() {
        super.viewDidLoad()
        self.listView.register(UINib(nibName: "MoviesCell", bundle: nil), forCellReuseIdentifier: "MoviesCell")
      
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.listView.delegate = adapter
        self.listView.dataSource = adapter
     
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        self.listView.delegate = nil
        self.listView.dataSource = nil
        super.viewWillDisappear(animated)
    }

}
