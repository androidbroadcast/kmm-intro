//
//  SearchVC.swift
//  MoviesIOS
//
//  Created by Anna Zharkova on 25.01.2021.
//

import UIKit

class SearchVC: UIViewController {
    private var adapter = MoviesAdapter()
    @IBOutlet weak var searchBar: UISearchBar!
    @IBOutlet weak var listView: UITableView!
    override func viewDidLoad() {
        super.viewDidLoad()
        self.listView.register(UINib(nibName: "MoviesCell", bundle: nil), forCellReuseIdentifier: "MoviesCell")
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.listView.delegate = adapter
        self.listView.dataSource = adapter
        self.searchBar.delegate = self
        
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        self.listView.delegate = nil
        self.listView.dataSource = nil
        self.searchBar.delegate = nil 
        super.viewWillDisappear(animated)
    }
    
    
}

extension SearchVC : UISearchBarDelegate {
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        searchBar.resignFirstResponder()
        searchBar.endEditing(true)
    }
    
    func searchBarCancelButtonClicked(_ searchBar: UISearchBar) {
        
        searchBar.text = ""
        searchBar.resignFirstResponder()
        searchBar.endEditing(true)
    }
}
