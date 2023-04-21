<?php

class Customer
{

    private $id;
    private $name;
    private $email;
    private $phone_number;

    public function __construct($id, $name, $email, $phone_number)
    {
        $this->id = $id;
        $this->name = $name;
        $this->email = $email;
        $this->phone_number = $phone_number;
    }

    public function getId()
    {
        return $this->id;
    }

    public function getName()
    {
        return $this->name;
    }

    public function getEmail()
    {
        return $this->email;
    }

    public function getPhone_Number()
    {
        return $this->phone_number;
    }

    public function setId($id)
    {
        $this->id = $id;
    }

    public function setName($name)
    {
        $this->name = $name;
    }

    public function setEmail($email)
    {
        $this->email = $email;
    }

    public function setPhone_number($phone_number)
    {
        $this->phone_number = $phone_number;
    }
}
    
?>